package com.github.bestheroz.demo.api.internal.mine;

import com.github.bestheroz.demo.api.internal.menu.MenuService;
import com.github.bestheroz.demo.api.internal.role.RoleChildrenDTO;
import com.github.bestheroz.demo.api.internal.role.RoleMapsDTO;
import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuChildrenDTO;
import com.github.bestheroz.demo.domain.Admin;
import com.github.bestheroz.demo.repository.AdminConfigRepository;
import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.demo.repository.RoleRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MineService {
  private final MenuService menuService;
  private final RoleRepository roleRepository;
  private final AdminRepository adminRepository;
  private final AdminConfigRepository adminConfigRepository;

  @Transactional(readOnly = true)
  public MineDTO getMyInfo() {
    return this.adminRepository
        .findById(AuthenticationUtils.getId())
        .map(MineDTO::new)
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
  }

  @Transactional(readOnly = true)
  public RoleMapsDTO getRole() {
    final RoleMapsDTO result =
        this.roleRepository
            .findById(AuthenticationUtils.getRoleId())
            .map(RoleMapsDTO::new)
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
    if (AuthenticationUtils.isSuperAdmin()) {
      result
          .getMaps()
          .addAll(this.menuService.getItems().stream().map(RoleMenuChildrenDTO::new).toList());
    }
    return result;
  }

  @Transactional(readOnly = true)
  public List<RoleChildrenDTO> getRoles(final Long id) {
    if (AuthenticationUtils.isSuperAdmin()) {
      return this.roleRepository
          .findAllByParentIdNullOrderByDisplayOrderAsc()
          .map(RoleChildrenDTO::new)
          .toList();
    } else {
      return this.roleRepository.findAllById(id).map(RoleChildrenDTO::new).toList();
    }
  }

  public EditMeDTO updateMe(final EditMeDTO payload) {
    return this.adminRepository
        .findById(AuthenticationUtils.getId())
        .map(
            admin -> {
              this.verifyPassword(admin.getPassword(), payload.getPassword());
              return new EditMeDTO(admin.setName(payload.getName()));
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
  }

  // 패스워드 검증 공통 함수
  public void verifyPassword(final String adminPassword, final String payload) {
    final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();

    // 패스워드가 틀리면
    if (!pbkdf2PasswordEncoder.matches(adminPassword, pbkdf2PasswordEncoder.encode(payload))) {
      throw new BusinessException(ExceptionCode.FAIL_MATCH_PASSWORD);
    }
  }

  public void changePassword(final ChangePasswordDTO payload) {
    final Admin admin =
        this.adminRepository
            .findById(AuthenticationUtils.getId())
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
    this.verifyPassword(admin.getPassword(), payload.getOldPassword());
    admin.setPassword(payload.getNewPassword());
  }

  public MineConfigDTO changeConfig(final MineConfigDTO payload) {
    return new MineConfigDTO(
        this.adminConfigRepository
            .findByAdminId(AuthenticationUtils.getId())
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN))
            .change(payload));
  }
}
