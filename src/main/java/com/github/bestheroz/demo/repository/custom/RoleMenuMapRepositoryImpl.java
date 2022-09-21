package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.QRoleMenuMap;
import com.github.bestheroz.demo.domain.RoleMenuMap;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleMenuMapRepositoryImpl implements RoleMenuMapRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;

  private static final QRoleMenuMap roleMenuMap = QRoleMenuMap.roleMenuMap;

  @Override
  public Stream<RoleMenuMap> findAllByRoleIdAndParentIdNullOrderByDisplayOrderAscAndMenuInnerJoined(
      final Long roleId) {
    return this.jpaQueryFactory
        .selectFrom(roleMenuMap)
        .innerJoin(roleMenuMap.menu)
        .fetchJoin()
        .where(roleMenuMap.role.id.eq(roleId), roleMenuMap.parent.id.isNull())
        .orderBy(roleMenuMap.displayOrder.asc())
        .stream();
  }
}
