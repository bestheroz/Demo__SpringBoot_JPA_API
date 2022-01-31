package com.github.bestheroz.demo.helper.recursive;

import java.util.List;

public abstract class RecursiveDTO<D, E> {
  public abstract Long getId();

  public abstract void setId(Long id);

  public abstract E toEntity(E e, Long key);

  public abstract List<D> getChildren();

  public abstract void setIdNull();
}
