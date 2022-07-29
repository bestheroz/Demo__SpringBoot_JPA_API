package com.github.bestheroz.demo.api.internal.admin;

import com.github.bestheroz.demo.api.internal.role.RoleService;
import com.github.bestheroz.demo.domain.Admin;
import com.github.bestheroz.demo.repository.AdminRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.filter.DataTableSortRequest;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

  private final EntityManager entityManager;
  private final AdminRepository adminRepository;
  private final RoleService roleService;
  private final PasswordEncoder passwordEncoder;

  private Admin persist(final Admin admin) {
    this.entityManager.persist(admin);
    return admin;
  }

  public AdminDTO persist(final AdminPasswordDTO dto) {
    dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
    return new AdminDTO(this.persist(dto.toAdmin()));
  }

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

    return this.adminRepository.getAllByPaginationAndSearch(
        search,
        adminFilter.getAvailableList(),
        adminFilter.getRoleIdList(),
        dataTableSortRequest.getPageRequest());
  }

  public AdminDTO change(final Long id, final AdminDTO payload) {
    return this.adminRepository
        .findById(id)
        .map(
            (admin) -> {
              if (!admin.getLoginId().equals(payload.getLoginId())) {
                if (this.adminRepository.existsByLoginIdAndDeletedIsFalseAndRoleDeletedIsFalse(
                    payload.getLoginId())) {
                  throw new BusinessException(ExceptionCode.FAIL_ALREADY_EXISTS_LOGIN_ID);
                }
              }
              return new AdminDTO(admin.change(payload));
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }

  public AdminDTO resetPassword(final Long id, final String password) {
    return this.adminRepository
        .findById(id)
        .map(
            (admin) -> {
              if (this.passwordEncoder.matches(password, admin.getPassword())) {
                throw new BusinessException(ExceptionCode.FAIL_SAME_PASSWORD);
              }
              return new AdminDTO(admin.changePassword(password));
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }

  public AdminDTO remove(final Long id) {
    return this.adminRepository
        .findById(id)
        .map(admin -> new AdminDTO(admin.remove()))
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }
}
