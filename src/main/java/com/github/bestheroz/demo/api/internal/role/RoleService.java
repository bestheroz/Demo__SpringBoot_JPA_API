package com.github.bestheroz.demo.api.internal.role;

import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuService;
import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntityHelper;
import com.github.bestheroz.demo.repository.RoleRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {

  private final EntityManager entityManager;
  private final RoleRepository roleRepository;
  private final RoleMenuService roleMenuService;

  private Role persist(final Role role) {
    this.entityManager.persist(role);
    return role;
  }

  public RoleChildrenDTO persist(final Long parentId, final RoleSimpleDTO dto) {
    return new RoleChildrenDTO(this.persist(dto.toRole(parentId, 1000, List.of())));
  }

  @Transactional(readOnly = true)
  public List<RoleChildrenDTO> getItems() {
    return this.roleRepository
        .findAllByParentIdNullAndDeletedIsFalseOrderByDisplayOrderAsc()
        .map(RoleChildrenDTO::new)
        .toList();
  }

  public List<RoleChildrenDTO> saveAll(final List<RoleChildrenDTO> dtos) {
    // 조회
    final List<Role> oldEntities = this.roleRepository.findAllByIdNotAndParentIdNull(1L).toList();

    final RecursiveEntityHelper<Role, RoleChildrenDTO> helper =
        new RecursiveEntityHelper<>(this.entityManager);

    // 이동된 root 엔티티 삭제
    final List<Role> entities = helper.deleteAndGetRemains(oldEntities, dtos, this.roleRepository);
    // 모두 저장
    return helper.saveAll(entities, dtos, null, null).map(RoleChildrenDTO::new).toList();
  }

  public Set<Long> getFlatRoleIdsByIdWithRecursiveChildren(final Long id) {
    final Set<Long> roleIdList = new HashSet<>();
    final Optional<Role> roleById = this.roleRepository.findById(id);
    roleById.ifPresent(
        (role) -> {
          roleIdList.add(role.getId());
          this.getFlatRoleIdsWithRecursiveChildren(roleIdList, role.getChildren());
        });
    return roleIdList;
  }

  private void getFlatRoleIdsWithRecursiveChildren(
      final Set<Long> result, final List<Role> children) {
    for (final Role child : children) {
      result.add(child.getId());
      if (!child.getChildren().isEmpty()) {
        this.getFlatRoleIdsWithRecursiveChildren(result, child.getChildren());
      }
    }
  }

  @Transactional(readOnly = true)
  public List<RoleSimpleDTO> getSelections(final Boolean availableFlag) {
    final Set<Long> ids;
    if (AuthenticationUtils.isSuperAdmin()) {
      ids = null;
    } else {
      ids = this.getFlatRoleIdsByIdWithRecursiveChildren(AuthenticationUtils.getRoleId());
    }
    return this.roleRepository
        .getAllByIdInAndAvailable(ids, availableFlag)
        .map(RoleSimpleDTO::new)
        .toList();
  }

  public RoleSimpleDTO put(final Long id, final RoleSimpleDTO payload) {
    return this.roleRepository
        .findById(id)
        .map(
            (role) -> {
              role.change(payload);
              this.entityManager.flush();
              return new RoleSimpleDTO(role);
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }

  public Set<Long> delete(final Long id) {
    final Set<Long> roleIdList = this.getFlatRoleIdsByIdWithRecursiveChildren(id);
    this.roleRepository.updateRoleDeletedToTrueByRoleId(roleIdList);
    return this.roleMenuService.deletedRoleMenuMap(roleIdList);
  }
}
