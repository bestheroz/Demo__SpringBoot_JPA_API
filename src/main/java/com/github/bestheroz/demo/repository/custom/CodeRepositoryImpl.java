package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodeRepositoryImpl implements CodeRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private static final QCode code = QCode.code;

  @Override
  public List<String> getTypesByAvailableFlag(final Boolean availableFlag) {
    final BooleanBuilder builder = new BooleanBuilder();
    if (availableFlag != null) {
      builder.and(code.availableFlag.eq(availableFlag));
    }
    return this.jpaQueryFactory
        .select(code.type)
        .from(code)
        .where(builder)
        .orderBy(code.type.asc())
        .distinct()
        .fetch();
  }
}
