package com.github.bestheroz.demo.api.internal.role.menu;

import com.github.bestheroz.demo.domain.RoleMenuMap;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntityHelper;
import com.github.bestheroz.demo.repository.RoleMenuMapRepository;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleMenuService {
  private final EntityManager entityManager;
  private final RoleMenuMapRepository roleMenuMapRepository;

  private RoleMenuMap persist(final RoleMenuMap roleMenuMap) {
    this.entityManager.persist(roleMenuMap);
    return roleMenuMap;
  }

  @Transactional(readOnly = true)
  public List<RoleMenuChildrenDTO> getItems(final Long roleId) {
    return this.roleMenuMapRepository
        .findAllByRoleIdAndParentIdNullOrderByDisplayOrderAscAndMenuInnerJoined(roleId)
        .map(RoleMenuChildrenDTO::new)
        .toList();
  }

  public List<RoleMenuChildrenDTO> saveAll(
      final Long roleId, final List<RoleMenuChildrenDTO> dtos) {
    // 조회
    final List<RoleMenuMap> oldEntities =
        this.roleMenuMapRepository.findAllByRoleIdAndParentIdNull(roleId).toList();

    final RecursiveEntityHelper<RoleMenuMap, RoleMenuChildrenDTO> helper =
        new RecursiveEntityHelper<>(this.entityManager);

    // 이동된 root 엔티티 삭제
    final List<RoleMenuMap> entities =
        helper.deleteAndGetRemains(oldEntities, dtos, this.roleMenuMapRepository);
    // 모두 저장
    return helper.saveAll(entities, dtos, null, roleId).map(RoleMenuChildrenDTO::new).toList();
  }

  public Set<Long> deletedRoleMenuMap(final Set<Long> ids) {
    this.roleMenuMapRepository.deleteByRoleIdIn(ids);
    return ids;
  }
}
