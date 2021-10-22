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
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "api/admins/")
@RequiredArgsConstructor
public class AdminController {
  private final AdminRepository adminRepository;
  private final AdminService adminService;

  @GetMapping(value = "codes/")
  public ResponseEntity<ApiResult<List<CodeVO<Long>>>> getCodes() {
    return Result.ok(this.adminRepository.getCodes());
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
    return Result.created(new AdminDTO(this.adminRepository.save(payload.toAdmin())));
  }

  @PatchMapping(value = "{id}")
  public ResponseEntity<ApiResult<AdminDTO>> patch(
      @PathVariable(value = "id") final Long id, @RequestBody @Valid final AdminDTO payload) {
    return Result.ok(this.adminService.change(id, payload));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult<?>> delete(@PathVariable(value = "id") final Long id) {
    this.adminRepository.deleteById(id);
    return Result.ok();
  }

  @PatchMapping(value = "{id}/reset-password")
  public ResponseEntity<ApiResult<AdminDTO>> resetPassword(
      @PathVariable(value = "id") final Long id, @RequestBody @Valid final String password) {
    return Result.ok(this.adminService.resetPassword(id, password));
  }
}
