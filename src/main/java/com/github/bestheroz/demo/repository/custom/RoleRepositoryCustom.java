package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.standard.common.exception.BusinessException;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryCustom {
  Stream<Role> getAllByIdInAndAvailable(final Set<Long> ids, final Boolean available)
      throws BusinessException;

  void updateRoleDeletedToTrueByRoleId(Set<Long> roleIds);
}
