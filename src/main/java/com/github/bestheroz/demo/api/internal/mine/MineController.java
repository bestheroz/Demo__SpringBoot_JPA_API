package com.github.bestheroz.demo.api.internal.mine;

import com.github.bestheroz.demo.api.internal.menu.MenuChildrenDTO;
import com.github.bestheroz.demo.api.internal.menu.MenuService;
import com.github.bestheroz.demo.api.internal.role.RoleChildrenDTO;
import com.github.bestheroz.demo.api.internal.role.RoleMapsDTO;
import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuService;
import com.github.bestheroz.demo.repository.AdminConfigRepository;
import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import com.github.bestheroz.standard.common.util.NullUtils;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mine")
@Slf4j
@RequiredArgsConstructor
public class MineController {
  private final AdminRepository adminRepository;
  private final AdminConfigRepository adminConfigRepository;
  private final MineService mineService;
  private final MenuService menuService;
  private final RoleMenuService roleMenuService;

  @GetMapping()
  public ResponseEntity<ApiResult<MineDTO>> getMyInfo() {
    return Result.ok(this.mineService.getMyInfo());
  }

  @PatchMapping()
  public ResponseEntity<ApiResult<EditMeDTO>> updateMe(
      @RequestBody @Valid final EditMeDTO payload) {
    return Result.ok(this.mineService.updateMe(payload));
  }

  @PatchMapping(value = "password")
  public ResponseEntity<ApiResult<?>> changePassword(
      @RequestBody @Valid final ChangePasswordDTO payload) {
    this.mineService.changePassword(payload);
    return Result.ok();
  }

  @PostMapping(value = "config")
  public ResponseEntity<ApiResult<MineConfigDTO>> changeConfig(
      @RequestBody @Valid final MineConfigDTO payload) {
    return Result.ok(this.mineService.changeConfig(payload));
  }

  @GetMapping(value = "config")
  public ResponseEntity<ApiResult<MineConfigDTO>> getConfig() {
    return Result.ok(
        this.adminConfigRepository
            .findByAdminId(AuthenticationUtils.getId())
            .map(MineConfigDTO::new)
            .orElseGet(MineConfigDTO::new));
  }

  @GetMapping(value = "role")
  public ResponseEntity<ApiResult<RoleMapsDTO>> getRole() {
    return Result.ok(this.mineService.getRole());
  }

  @GetMapping("roles/")
  ResponseEntity<ApiResult<List<RoleChildrenDTO>>> getRoles() {
    return Result.ok(this.mineService.getRoles(AuthenticationUtils.getRoleId()));
  }

  @GetMapping(value = "roles/selections/")
  public ResponseEntity<ApiResult<List<RoleSimpleDTO>>> getAllFlat() {
    final List<RoleChildrenDTO> roleChildrenDTOS =
        this.mineService.getRoles(AuthenticationUtils.getRoleId());
    final List<RoleSimpleDTO> result = new ArrayList<>();
    this.getRoleSimpleDTOWithRecursiveChildren(result, roleChildrenDTOS);
    return Result.ok(result);
  }

  private void getRoleSimpleDTOWithRecursiveChildren(
      final List<RoleSimpleDTO> result, final List<RoleChildrenDTO> roleChildrenDTOS) {
    for (final RoleChildrenDTO roleChildrenDTO : roleChildrenDTOS) {
      result.add(new RoleSimpleDTO(roleChildrenDTO));
      if (NullUtils.isNotEmpty(roleChildrenDTO.getChildren())) {
        this.getRoleSimpleDTOWithRecursiveChildren(result, roleChildrenDTO.getChildren());
      }
    }
  }

  @PostMapping(value = "verify-password")
  public ResponseEntity<ApiResult<?>> getVerifyPassword(@RequestBody @Valid final String password) {
    return this.adminRepository
        .findById(AuthenticationUtils.getId())
        .map(
            admin -> {
              this.mineService.verifyPassword(admin.getPassword(), password);
              return Result.ok();
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_ADMIN));
  }

  @GetMapping("menus/")
  public ResponseEntity<ApiResult<List<MenuChildrenDTO>>> getMenus() {
    if (AuthenticationUtils.isSuperAdmin()) {
      return Result.ok(this.menuService.getItems());
    } else {
      return Result.ok(
          this.menuService.getMenuChildrenDTOWithRecursiveChildren(
              this.roleMenuService.getItems(AuthenticationUtils.getRoleId())));
    }
  }
}
