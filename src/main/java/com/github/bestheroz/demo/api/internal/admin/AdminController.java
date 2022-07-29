package com.github.bestheroz.demo.api.internal.admin;

import com.github.bestheroz.demo.api.internal.code.CodeVO;
import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.standard.common.filter.DataTableSortRequest;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/admins/")
@RequiredArgsConstructor
public class AdminController {
  private final AdminRepository adminRepository;
  private final AdminService adminService;

  @GetMapping(value = "codes/")
  public ResponseEntity<ApiResult<List<CodeVO<Long>>>> getCodeVOs() {
    return Result.ok(this.adminRepository.getCodeVOs());
  }

  @GetMapping
  public ResponseEntity<ApiResult<Page<AdminDTO>>> getAdmins(
      @RequestParam(required = false) final String search,
      final DataTableSortRequest dataTableSortRequest,
      final AdminFilter adminFilter) {

    return Result.ok(this.adminService.getAdmins(search, dataTableSortRequest, adminFilter));
  }

  @PostMapping
  public ResponseEntity<ApiResult<AdminDTO>> post(
      @RequestBody @Valid final AdminPasswordDTO payload) {
    return Result.created(this.adminService.persist(payload));
  }

  @PatchMapping(value = "{id}")
  public ResponseEntity<ApiResult<AdminDTO>> patch(
      @PathVariable(value = "id") final Long id, @RequestBody @Valid final AdminDTO payload) {
    return Result.ok(this.adminService.change(id, payload));
  }

  @PatchMapping(value = "{id}/reset-password")
  public ResponseEntity<ApiResult<AdminDTO>> resetPassword(
      @PathVariable(value = "id") final Long id, @RequestBody @Valid final String password) {
    return Result.ok(this.adminService.resetPassword(id, password));
  }

  @GetMapping(value = "exists-login-id")
  public ResponseEntity<ApiResult<Boolean>> existsLoginId(
      @RequestParam(required = false) final String loginId) {
    return Result.ok(
        this.adminRepository.existsByLoginIdAndDeletedIsFalseAndRoleDeletedIsFalse(loginId));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult<AdminDTO>> remove(@PathVariable(value = "id") final Long id) {
    return Result.ok(this.adminService.remove(id));
  }
}
