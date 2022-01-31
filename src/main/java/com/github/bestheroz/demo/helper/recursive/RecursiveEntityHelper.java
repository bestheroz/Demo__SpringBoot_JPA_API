package com.github.bestheroz.demo.helper.recursive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.springframework.data.repository.CrudRepository;

public class RecursiveEntityHelper<E extends RecursiveEntity<E, D>, D extends RecursiveDTO<D, E>> {

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
      final List<E> entities,
      final List<D> dtos,
      final EntityManager entityManager,
      final E parent,
      final Long key) {

    // 같은 뎁스에서 수정
    this.changeEntityAll(entities, dtos, parent);
    // 추가
    final List<E> results = this.addAll(entities, dtos, entityManager, parent, key);
    // 정렬
    this.sort(results, dtos);
    return results.stream();
  }

  // 같은 뎁스에서 수정
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
      final List<E> entities,
      final List<D> dtos,
      final EntityManager entityManager,
      final E parent,
      final Long key) {
    final List<E> results = new ArrayList<>(entities);
    // 추가
    results.addAll(
        dtos.stream()
            .filter(dto -> entities.stream().noneMatch(e -> e.getId().equals(dto.getId())))
            .map(
                dto -> {
                  dto.setIdNull();
                  final E entity = dto.toEntity(parent, key);
                  entityManager.persist(entity);
                  dto.setId(entity.getId());
                  return entity;
                })
            .toList());
    entityManager.flush();
    return results;
  }

  // 정렬
  private void sort(final List<E> entities, final List<D> dtos) {
    final AtomicInteger displayOrderIndex = new AtomicInteger();
    dtos.forEach(
        dto ->
            entities.stream()
                .filter(entity -> entity.getId().equals(dto.getId()))
                .findFirst()
                .ifPresent(
                    entity -> {
                      entity.setDisplayOrder(displayOrderIndex.incrementAndGet());
                    }));
    entities.sort(Comparator.comparingInt(E::getDisplayOrder));
  }
}
