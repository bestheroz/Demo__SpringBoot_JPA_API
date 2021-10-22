package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.App;
import com.github.bestheroz.demo.domain.QApp;
import com.github.bestheroz.demo.type.AppPlatformType;
import com.github.bestheroz.standard.common.util.PagingUtils;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class AppRepositoryImpl implements AppRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private static final QApp app = QApp.app;

  @Override
  public Page<App> findAllBySearch(
      final String search,
      final List<Boolean> availableList,
      final List<AppPlatformType> appPlatformTypeList,
      final Pageable pageable) {

    final QueryResults<App> result =
        this.jpaQueryFactory
            .selectFrom(app)
            .where(
                this.eqName(search),
                this.inAppPlatformType(appPlatformTypeList),
                this.inAvailable(availableList))
            .orderBy(PagingUtils.getOrderBy(pageable.getSort(), App.class, "app"))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
    return new PageImpl<>(result.getResults(), pageable, result.getTotal());
  }

  private BooleanExpression eqName(final String search) {
    return StringUtils.isEmpty(search) ? null : app.name.contains(search);
  }

  private BooleanExpression inAppPlatformType(final List<AppPlatformType> appPlatformTypeList) {
    return appPlatformTypeList == null ? null : app.platform.in(appPlatformTypeList);
  }

  private BooleanExpression inAvailable(final List<Boolean> availableList) {
    return availableList == null ? null : app.available.in(availableList);
  }
}
