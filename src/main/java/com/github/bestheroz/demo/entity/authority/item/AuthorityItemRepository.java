package com.github.bestheroz.demo.entity.authority.item;

import com.github.bestheroz.demo.entity.menu.MenuEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityItemRepository extends CrudRepository<AuthorityItemEntity, Long> {
  List<AuthorityItemEntity> findAllByAuthorityOrderByDisplayOrderAsc(String authority);

  AuthorityItemEntity deleteByMenu(MenuEntity menu);
}
