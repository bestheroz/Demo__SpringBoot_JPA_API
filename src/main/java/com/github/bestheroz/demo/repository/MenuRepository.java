package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.Menu;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
  List<Menu> findAllByParentIdNullOrderByDisplayOrderAsc();
}
