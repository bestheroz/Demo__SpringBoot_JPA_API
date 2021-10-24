package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.QRole;
import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private static final QRole role = QRole.role;

  @Override
  public List<Role> getRolesByIdInAndAvailable(final List<Long> ids, final Boolean available)
      throws BusinessException {
    final BooleanBuilder builder = new BooleanBuilder();
    if (ids != null) {
      builder.and(role.id.in(ids));
    }

    if (available != null) {
      builder.and(role.available.eq(available));
    }

    return this.jpaQueryFactory.select(role).from(role).where(builder).fetch();
  }
}
