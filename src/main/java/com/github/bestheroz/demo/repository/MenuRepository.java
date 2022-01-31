package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.Menu;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
  Stream<Menu> findAllByParentIdNullOrderByDisplayOrderAsc();

  Stream<Menu> findAllByParentIdNull();
}
