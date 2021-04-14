package com.github.bestheroz.demo.api.internal.member;

import com.github.bestheroz.demo.entity.code.CodeRepository;
import com.github.bestheroz.demo.entity.member.MemberEntity;
import com.github.bestheroz.demo.entity.member.MemberRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/members")
@Slf4j
public class MemberController {
  @Resource private MemberRepository memberRepository;
  @Resource private MemberService memberService;
  @Resource private CodeRepository codeRepository;

  @GetMapping(value = "codes")
  ResponseEntity<ApiResult> getItems() {
    return Result.ok(this.codeRepository.getMembers());
  }

  @GetMapping(value = "mine")
  ResponseEntity<ApiResult> getMyInfo() {
    return Result.ok(
        this.memberRepository
            .findById(AuthenticationUtils.getId())
            .map(
                item -> {
                  item.setPassword(null);
                  return item;
                })
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER)));
  }

  @PatchMapping("mine")
  public ResponseEntity<ApiResult> editMe(@RequestBody final MemberEntity payload) {
    return this.memberRepository
        .findById(AuthenticationUtils.getId())
        .map(
            memberEntity -> {
              final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
              // 패스워드가 틀리면
              if (!pbkdf2PasswordEncoder.matches(
                  memberEntity.getPassword(),
                  pbkdf2PasswordEncoder.encode(payload.getPassword()))) {
                throw new BusinessException(ExceptionCode.FAIL_MATCH_PASSWORD);
              }
              memberEntity.setName(payload.getName());
              this.memberRepository.save(memberEntity);
              return Result.ok();
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER));
  }

  @PostMapping(value = "mine/changePassword")
  public ResponseEntity<ApiResult> changePassword(@RequestBody final Map<String, String> payload) {
    return this.memberRepository
        .findById(AuthenticationUtils.getId())
        .map(
            memberEntity -> {
              final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
              // 패스워드가 틀리면
              if (!pbkdf2PasswordEncoder.matches(
                  memberEntity.getPassword(),
                  pbkdf2PasswordEncoder.encode(payload.get("oldPassword")))) {
                throw new BusinessException(ExceptionCode.FAIL_MATCH_OLD_PASSWORD);
              }
              memberEntity.setPassword(payload.get("newPassword"));
              this.memberRepository.save(memberEntity);
              return Result.ok();
            })
        .orElseThrow(
            () -> {
              // 1. 유저가 없으면
              return new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER);
            });
  }

  @PostMapping(value = "mine/changeTheme")
  public ResponseEntity<ApiResult> changeTheme(@RequestBody final Map<String, String> payload) {
    return this.memberRepository
        .findById(AuthenticationUtils.getId())
        .map(
            memberEntity -> {
              memberEntity.setTheme(payload.get("theme"));
              this.memberRepository.save(memberEntity);
              return Result.ok();
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER));
  }

  @GetMapping(value = "mine/authority")
  ResponseEntity<ApiResult> getAuthority() {
    return Result.ok(this.memberService.getItem(AuthenticationUtils.getId()));
  }
}
