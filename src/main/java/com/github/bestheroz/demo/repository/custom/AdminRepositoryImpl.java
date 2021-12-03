package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.api.internal.code.CodeVO;
import com.github.bestheroz.demo.domain.Admin;
import com.github.bestheroz.demo.domain.QAdmin;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import com.github.bestheroz.standard.common.util.NullUtils;
import com.github.bestheroz.standard.common.util.PagingUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private static final QAdmin admin = QAdmin.admin;

  @Override
  public Page<Admin> findAllBySearch(
      final String search,
      final List<Boolean> availableList,
      final List<Long> roleIdList,
      final Pageable pageable) {

    final BooleanBuilder builder = new BooleanBuilder();

    // SUPER 관리자 아닌 일반관리자 또는 사용자 개별 조건
    if (AuthenticationUtils.isNotSuperAdmin()) {
      builder.and(admin.role.id.ne(1L));
    }

    // 검색조건 (사용자ID , 사용자명)
    if (StringUtils.isNotEmpty(search)) {
      builder.and(admin.loginId.contains(search).or(admin.name.contains(search)));
    }

    // 필터조건 (권한)
    if (NullUtils.isNotEmpty(roleIdList)) {
      builder.and(admin.role.id.in(roleIdList));
    }

    // 필터조건 (사용유무)
    if (availableList != null && availableList.size() > 0) {
      builder.and(admin.available.in(availableList));
    }

    final QueryResults<Admin> result =
        this.jpaQueryFactory
            .selectFrom(admin)
            .where(builder)
            .orderBy(PagingUtils.getOrderBy(pageable.getSort(), Admin.class, "admin"))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
    return new PageImpl<>(result.getResults(), pageable, result.getTotal());
  }

  @Override
  public List<CodeVO<Long>> getCodes() throws BusinessException {
    return this.jpaQueryFactory.select(admin.id, admin.name).from(admin).fetch().stream()
        .map(tuple -> new CodeVO<>(tuple.get(0, Long.class), tuple.get(1, String.class)))
        .toList();
  }
}
