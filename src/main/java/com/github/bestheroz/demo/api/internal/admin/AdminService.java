package com.github.bestheroz.demo.api.internal.admin;

import com.github.bestheroz.demo.api.internal.role.RoleService;
import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.filter.DataTableSortRequest;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
  private final AdminRepository adminRepository;
  private final RoleService roleService;

  @Transactional(readOnly = true)
  public Page<AdminDTO> getAdmins(
      final String search,
      final DataTableSortRequest dataTableSortRequest,
      final AdminFilter adminFilter) {
    if (adminFilter.getRoleIdList().size() == 0 && AuthenticationUtils.isNotSuperAdmin()) {
      adminFilter
          .getRoleIdList()
          .addAll(
              this.roleService.getFlatRoleIdsByIdWithRecursiveChildren(
                  AuthenticationUtils.getRoleId()));
    }

    return this.adminRepository
        .findAllBySearch(
            search,
            adminFilter.getAvailableList(),
            adminFilter.getRoleIdList(),
            dataTableSortRequest.getPageRequest())
        .map(AdminDTO::new);
  }

  public AdminDTO change(final Long id, final AdminDTO payload) {
    return this.adminRepository
        .findById(id)
        .map(
            (item) -> {
              item.change(payload);
              return new AdminDTO(this.adminRepository.save(item));
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }

  public AdminDTO resetPassword(final Long id, final String password) {
    return this.adminRepository
        .findById(id)
        .map(
            (item) -> {
              item.setPassword(password);
              return new AdminDTO(this.adminRepository.save(item));
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }
}
