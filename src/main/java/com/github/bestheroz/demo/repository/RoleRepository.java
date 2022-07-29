package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.demo.repository.custom.RoleRepositoryCustom;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, RoleRepositoryCustom {
  Stream<Role> findAllByParentIdNullAndDeletedIsFalseOrderByDisplayOrderAsc();

  Stream<Role> findAllById(Long id);

  Stream<Role> findAllByIdNotAndParentIdNull(Long id);
}
