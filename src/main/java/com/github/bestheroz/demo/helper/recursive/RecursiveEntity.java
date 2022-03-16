package com.github.bestheroz.demo.helper.recursive;

public abstract class RecursiveEntity<E, D> {

  public abstract Long getId();

  public abstract Integer getDisplayOrder();

  public abstract E setDisplayOrder(final Integer displayOrder);

  public abstract E change(final D dto, final E e);
}
