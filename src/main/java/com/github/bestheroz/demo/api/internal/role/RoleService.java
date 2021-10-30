package com.github.bestheroz.demo.api.internal.role;

import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.demo.repository.RoleRepository;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import com.github.bestheroz.standard.common.util.NullUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepository;

  @Transactional(readOnly = true)
  public List<RoleChildrenDTO> getItems() {
    return this.roleRepository.findAllByParentIdNullOrderByDisplayOrderAsc().stream()
        .map(RoleChildrenDTO::new)
        .toList();
  }

  public List<RoleChildrenDTO> saveAll(final List<RoleChildrenDTO> payload) {
    if (AuthenticationUtils.isSuperAdmin()) {
      this.roleRepository.deleteByIdNotInAndParentIdNull(
          payload.stream().map(RoleChildrenDTO::getId).filter(Objects::nonNull).toList());
    }
    return IterableUtils.toList(
            this.roleRepository.saveAll(this.getRoleWithRecursiveChildren(payload, null)))
        .stream()
        .map(RoleChildrenDTO::new)
        .toList();
  }

  private List<Role> getRoleWithRecursiveChildren(
      final List<RoleChildrenDTO> roleChildrenDTOS, final Role parent) {
    int displayOrder = 1;
    final List<Role> result = new ArrayList<>();
    for (final RoleChildrenDTO roleChildrenDTO : roleChildrenDTOS) {
      final Role role = roleChildrenDTO.toRole(parent, displayOrder++);
      if (NullUtils.isNotEmpty(roleChildrenDTO.getChildren())) {
        role.getChildren()
            .addAll(this.getRoleWithRecursiveChildren(roleChildrenDTO.getChildren(), role));
      }
      result.add(role);
    }
    return result;
  }

  public List<Long> getFlatRoleIdsByIdWithRecursiveChildren(final Long id) {
    final List<Long> roleIdList = new ArrayList<>();
    final Optional<Role> roleById = this.roleRepository.findById(id);
    roleById.ifPresent(
        (role) -> {
          roleIdList.add(role.getId());
          this.getFlatRoleIdsWithRecursiveChildren(roleIdList, role.getChildren());
        });
    return roleIdList;
  }

  private void getFlatRoleIdsWithRecursiveChildren(
      final List<Long> result, final List<Role> children) {
    for (final Role child : children) {
      result.add(child.getId());
      if (!child.getChildren().isEmpty()) {
        this.getFlatRoleIdsWithRecursiveChildren(result, child.getChildren());
      }
    }
  }

  @Transactional(readOnly = true)
  public List<RoleSimpleDTO> getSelections(final Boolean available) {
    final List<Long> ids;
    if (AuthenticationUtils.isSuperAdmin()) {
      ids = null;
    } else {
      ids = this.getFlatRoleIdsByIdWithRecursiveChildren(AuthenticationUtils.getRoleId());
    }
    return this.roleRepository.getRolesByIdInAndAvailable(ids, available).stream()
        .map(RoleSimpleDTO::new)
        .toList();
  }
}
