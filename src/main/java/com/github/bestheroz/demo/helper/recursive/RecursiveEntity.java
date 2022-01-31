package com.github.bestheroz.demo.helper.recursive;

import com.github.bestheroz.demo.domain.BaseAuditEntity;

public abstract class RecursiveEntity<E, D> extends BaseAuditEntity {

  public abstract Long getId();

  public abstract Integer getDisplayOrder();

  public abstract E setDisplayOrder(final Integer displayOrder);

  public abstract E change(final D dto, final E e);
}
