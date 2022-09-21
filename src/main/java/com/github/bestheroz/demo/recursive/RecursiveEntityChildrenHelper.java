package com.github.bestheroz.demo.recursive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RecursiveEntityChildrenHelper<
    E extends RecursiveEntity<E, D>, D extends RecursiveDTO<D, E>> {

  RecursiveEntityHelper<E, D> helper = new RecursiveEntityHelper<>();

  // 이동된 this 엔티티 삭제
  public void deleteAndGetRemains(final List<E> entities, final List<D> dtos) {
    if (dtos.isEmpty()) {
      entities.clear();
    } else {
      entities.removeAll(
          entities.stream()
              .filter(entity -> dtos.stream().noneMatch(dto -> entity.getId().equals(dto.getId())))
              .toList());
    }
  }

  public void saveAll(final List<E> entities, final List<D> dtos, final E parent, final Long key) {

    // 같은 뎁스에서 수정
    this.helper.changeEntityAll(entities, dtos, parent);
    // this 로 이동한 데이터는 id 를 삭제하여 새로 추가 되도록 한다
    this.setNullIdOfDTOForAddAndSort(entities, dtos, parent, key);
  }

  // this 로 이동한 데이터는 id 를 삭제하여 새로 추가 되도록 한다
  private void setNullIdOfDTOForAddAndSort(
      final List<E> entities, final List<D> dtos, final E parent, final Long key) {
    final AtomicInteger index = new AtomicInteger();
    final List<E> newEntities = new ArrayList<>();
    dtos.stream()
        .forEach(
            dto -> {
              entities.stream()
                  .filter(entity -> entity.getId().equals(dto.getId()))
                  .findFirst()
                  .ifPresentOrElse(
                      entity -> entity.setDisplayOrder(index.incrementAndGet()),
                      () -> {
                        dto.setIdNull();
                        newEntities.add(
                            dto.toEntity(parent, key).setDisplayOrder(index.incrementAndGet()));
                      });
            });
    entities.addAll(newEntities);
    entities.sort(Comparator.comparingInt(E::getDisplayOrder));
  }
}
