package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.demo.repository.custom.RoleRepositoryCustom;
import java.util.List;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, RoleRepositoryCustom {
  List<Role> findAllByParentIdNullOrderByDisplayOrderAsc();

  List<Role> findAllById(Long id);

  void deleteByIdNotInAndParentIdNull(Set<Long> deleteIds);
}
