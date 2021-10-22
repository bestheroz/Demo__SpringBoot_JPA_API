package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.QRoleMenuMap;
import com.github.bestheroz.demo.domain.RoleMenuMap;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleMenuMapRepositoryImpl implements RoleMenuMapRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;

  private static final QRoleMenuMap roleMenuMap = QRoleMenuMap.roleMenuMap;

  @Override
  public List<RoleMenuMap> findAllByRoleIdAndParentIdNullOrderByDisplayOrderAscAndMenuInnerJoined(
      final Long roleId) {
    return this.jpaQueryFactory
        .select(roleMenuMap)
        .from(roleMenuMap)
        .innerJoin(roleMenuMap.menu)
        .fetchJoin()
        .where(roleMenuMap.role.id.eq(roleId))
        .where(roleMenuMap.parent.id.isNull())
        .orderBy(roleMenuMap.displayOrder.asc())
        .fetch();
  }
}
