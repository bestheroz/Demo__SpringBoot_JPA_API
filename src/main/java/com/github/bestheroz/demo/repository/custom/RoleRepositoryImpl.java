package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.QRole;
import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleRepositoryImpl implements RoleRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private static final QRole role = QRole.role;

  @Override
  public Stream<Role> getAllByIdInAndAvailable(final Set<Long> ids, final Boolean availableFlag)
      throws BusinessException {
    final BooleanBuilder builder = new BooleanBuilder();
    if (ids != null) {
      builder.and(role.id.in(ids));
    }

    if (availableFlag != null) {
      builder.and(role.availableFlag.eq(availableFlag));
    }
    builder.and(role.deleted.eq(false));
    return this.jpaQueryFactory.selectFrom(role).where(builder).stream();
  }

  @Override
  public void updateRoleDeletedToTrueByRoleId(final Set<Long> roleIds) {
    this.jpaQueryFactory
        .update(role)
        .set(role.deleted, true)
        .set(role.updatedBy, AuthenticationUtils.getId())
        .set(role.updated, Instant.now())
        .where(role.id.in(roleIds))
        .execute();
  }
}
