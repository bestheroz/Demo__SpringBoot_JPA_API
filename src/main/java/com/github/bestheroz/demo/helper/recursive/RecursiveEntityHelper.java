package com.github.bestheroz.demo.helper.recursive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;

@NoArgsConstructor
public class RecursiveEntityHelper<E extends RecursiveEntity<E, D>, D extends RecursiveDTO<D, E>> {

  EntityManager entityManager;

  public RecursiveEntityHelper(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  // 이동된 root 엔티티 삭제
  public List<E> deleteAndGetRemains(
      final List<E> entities, final List<D> dtos, final CrudRepository<E, Long> repository) {
    if (entities.size() == dtos.size()) {
      return entities;
    }
    final List<E> removeEntities =
        entities.stream()
            .filter(entity -> dtos.stream().noneMatch(dto -> entity.getId().equals(dto.getId())))
            .toList();
    repository.deleteAll(removeEntities);

    return entities.stream()
        .filter(
            entity ->
                removeEntities.stream()
                    .noneMatch(removed -> entity.getId().equals(removed.getId())))
        .toList();
  }

  public final Stream<E> saveAll(
      final List<E> entities, final List<D> dtos, final E parent, final Long key) {

    // 같은 부모내에서 수정
    this.changeEntityAll(entities, dtos, parent);

    // 추가
    final List<E> results = this.addAll(entities, dtos, parent, key);

    // 정렬
    this.sort(results, dtos);
    this.entityManager.clear();
    return results.stream();
  }

  // 같은 부모내에서 수정
  protected void changeEntityAll(final List<E> entities, final List<D> dtos, final E parent) {
    entities.forEach(
        entity ->
            dtos.stream()
                .filter(dto -> entity.getId().equals(dto.getId()))
                .findFirst()
                .ifPresent(dto -> entity.change(dto, parent)));
  }

  // 추가
  private List<E> addAll(
      final List<E> entities, final List<D> dtos, final E parent, final Long key) {
    final List<E> results = new ArrayList<>(entities);
    // 추가
    results.addAll(
        dtos.stream()
            .filter(dto -> entities.stream().noneMatch(e -> e.getId().equals(dto.getId())))
            .map(
                dto -> {
                  dto.setIdNull();
                  // 추가된 entity 는 정렬된 displayOrder 값을 위해 sort 에서 persist 한다.
                  return dto.toEntity(parent, key);
                })
            .toList());
    return results;
  }

  // 정렬
  private void sort(final List<E> entities, final List<D> dtos) {
    final AtomicInteger displayOrderIndex = new AtomicInteger();
    dtos.forEach(
        dto ->
            entities.stream()
                .filter(entity -> Objects.equals(entity.getId(), dto.getId()))
                .findFirst()
                .ifPresent(
                    entity -> {
                      entity.setDisplayOrder(displayOrderIndex.incrementAndGet());
                      this.entityManager.persist(entity);
                    }));
    entities.sort(Comparator.comparingInt(E::getDisplayOrder));
  }
}
