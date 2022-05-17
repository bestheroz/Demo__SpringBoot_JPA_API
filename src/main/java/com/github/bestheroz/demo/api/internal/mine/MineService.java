package com.github.bestheroz.demo.api.internal.mine;

import com.github.bestheroz.demo.api.internal.menu.MenuService;
import com.github.bestheroz.demo.api.internal.role.RoleChildrenDTO;
import com.github.bestheroz.demo.api.internal.role.RoleMapsDTO;
import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuChildrenDTO;
import com.github.bestheroz.demo.domain.Admin;
import com.github.bestheroz.demo.domain.AdminConfig;
import com.github.bestheroz.demo.repository.AdminConfigRepository;
import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.demo.repository.RoleRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MineService {
  private final EntityManager entityManager;
  private final MenuService menuService;
  private final RoleRepository roleRepository;
  private final AdminRepository adminRepository;
  private final AdminConfigRepository adminConfigRepository;
  private final PasswordEncoder passwordEncoder;

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
              this.verifyPassword(payload.getPassword(), admin.getPassword());
              return new EditMeDTO(admin.changeName(payload.getName()));
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
  }

  // 패스워드 검증 공통 함수
  public void verifyPassword(final String rawPassword, final String encodedPassword) {
    // 패스워드가 틀리면
    if (!this.passwordEncoder.matches(rawPassword, encodedPassword)) {
      throw new BusinessException(ExceptionCode.FAIL_MATCH_PASSWORD);
    }
  }

  public void changePassword(final ChangePasswordDTO payload) {
    final Admin admin =
        this.adminRepository
            .findById(AuthenticationUtils.getId())
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
    this.verifyPassword(payload.getOldPassword(), admin.getPassword());
    admin.changePassword(payload.getNewPassword());
  }

  public MineConfigDTO changeConfig(final MineConfigDTO payload) {
    return this.adminConfigRepository
        .findByAdminId(AuthenticationUtils.getId())
        .map(
            (adminConfig) -> {
              adminConfig.change(payload);
              this.entityManager.flush();
              return new MineConfigDTO(adminConfig);
            })
        .orElseGet(
            () -> {
              final Admin admin =
                  this.adminRepository
                      .findById(AuthenticationUtils.getId())
                      .orElseThrow(
                          () -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
              final AdminConfig adminConfig = payload.toAdminConfig(admin);
              this.entityManager.persist(adminConfig);
              return new MineConfigDTO(adminConfig);
            });
  }
}
