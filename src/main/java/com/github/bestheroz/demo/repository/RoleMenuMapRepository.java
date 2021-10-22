package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.RoleMenuMap;
import com.github.bestheroz.demo.repository.custom.RoleMenuMapRepositoryCustom;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuMapRepository
    extends CrudRepository<RoleMenuMap, Long>, RoleMenuMapRepositoryCustom {
  void deleteByRoleIdAndIdNotInAndParentIdNull(Long roleId, List<Long> deleteIds);

  void deleteByRoleId(Long roleId);
}
