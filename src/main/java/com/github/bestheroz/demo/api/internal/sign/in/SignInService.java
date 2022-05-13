package com.github.bestheroz.demo.api.internal.sign.in;

import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.standard.common.authenticate.CustomUserDetails;
import com.github.bestheroz.standard.common.authenticate.JwtTokenProvider;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SignInService implements UserDetailsService {
  private final AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(final String loginId) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(loginId)) {
      throw new UsernameNotFoundException("No user found");
    }
    return this.adminRepository
        .findByLoginId(loginId)
        .map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by `" + loginId + "`"));
  }

  public TokenDTO signIn(final String loginId, final String password) {
    return this.adminRepository
        .findByLoginId(loginId)
        .map(
            admin -> {
              // 1. 역할 체크
              if (!admin.getRole().getAvailable()) {
                throw new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN);
              }

              // 2. 패스워드가 틀리면
              if (!this.passwordEncoder.matches(password, admin.getPassword())) {
                admin.plusSignInFailCnt();
                return new TokenDTO(null, null);
              }

              // 3. SIGN_IN_FAIL_CNT가 5회 이상 인가
              if (admin.getSignInFailCnt() >= 5) {
                throw new BusinessException(ExceptionCode.FAIL_SIGN_IN_FAIL_CNT);
              }

              // 4. 계정 차단된 상태인가
              if (!admin.getAvailable()
                  || admin.getExpired().toEpochMilli() < Instant.now().toEpochMilli()) {
                throw new BusinessException(ExceptionCode.FAIL_SIGN_IN_CLOSED);
              }
              admin.resetSignInFailCnt();
              final CustomUserDetails customUserDetails = new CustomUserDetails(admin);
              final String accessToken = JwtTokenProvider.createAccessToken(customUserDetails);
              final String refreshToken = JwtTokenProvider.createRefreshToken(customUserDetails);
              admin.signedSuccess(refreshToken);
              return new TokenDTO(accessToken, refreshToken);
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
  }

  @Transactional
  public TokenDTO getNewToken(final String refreshToken) {
    return this.adminRepository
        .findByIdAndToken(JwtTokenProvider.getId(refreshToken), refreshToken)
        .map(
            admin -> {
              final CustomUserDetails customUserDetails = new CustomUserDetails(admin);
              final String newAccessToken = JwtTokenProvider.createAccessToken(customUserDetails);
              SecurityContextHolder.getContext()
                  .setAuthentication(JwtTokenProvider.getAuthentication(newAccessToken));
              // refreshToken 이 생성된지 5초 이내에 요청이 들어오면 존재하는 refreshToken 을 반환하자.
              if (JwtTokenProvider.issuedRefreshTokenIn3Seconds(refreshToken)) {
                return new TokenDTO(newAccessToken, admin.getToken());
              }
              // 동시 로그인을 허용하려면 refreshToken 은 새로 업데이트 하면 안된다.
              final String newRefreshToken = JwtTokenProvider.createRefreshToken(customUserDetails);
              admin.setToken(newRefreshToken);
              return new TokenDTO(newAccessToken, newRefreshToken);
            })
        .orElseThrow(
            () -> {
              log.info("invalid refresh-token");
              return new BusinessException(ExceptionCode.FAIL_TRY_SIGN_IN_FIRST);
            });
  }
}
