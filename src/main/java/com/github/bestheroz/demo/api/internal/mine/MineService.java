package com.github.bestheroz.demo.api.internal.mine;

import com.github.bestheroz.demo.api.internal.menu.MenuService;
import com.github.bestheroz.demo.api.internal.role.RoleChildrenDTO;
import com.github.bestheroz.demo.api.internal.role.RoleMapsDTO;
import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuChildrenDTO;
import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.demo.repository.RoleRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MineService {
  private final MenuService menuService;
  private final RoleRepository roleRepository;
  private final AdminRepository adminRepository;

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
      return this.roleRepository.findAllByParentIdNullOrderByDisplayOrderAsc().stream()
          .map(RoleChildrenDTO::new)
          .toList();
    } else {
      return this.roleRepository.findAllById(id).stream().map(RoleChildrenDTO::new).toList();
    }
  }
}
