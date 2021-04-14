package com.github.bestheroz.demo.entity.menu;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity, Long> {
  List<MenuEntity> findAll(Sort sort);
}
