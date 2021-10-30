package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.api.internal.code.CodeVO;
import com.github.bestheroz.demo.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CodeRepositoryImpl implements CodeRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private static final QCode code = QCode.code;

  @Override
  public List<String> getTypesByAvailable(final Boolean available) {
    final BooleanBuilder builder = new BooleanBuilder();
    if (available != null) {
      builder.and(code.available.eq(available));
    }
    return this.jpaQueryFactory
        .select(code.type)
        .from(code)
        .where(builder)
        .orderBy(code.type.asc())
        .distinct()
        .fetch();
  }

  @Override
  public List<CodeVO<String>> getCodesByTypeAndAvailableOrderByDisplayOrder(
      final String type, final Boolean available) {
    return this.jpaQueryFactory
        .select(code.value, code.text)
        .from(code)
        .where(code.type.eq(type), code.available.eq(available))
        .orderBy(code.displayOrder.asc())
        .fetch()
        .stream()
        .map(tuple -> new CodeVO<>(tuple.get(0, String.class), tuple.get(1, String.class)))
        .toList();
  }
}
