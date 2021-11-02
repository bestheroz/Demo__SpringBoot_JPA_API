package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.standard.common.exception.BusinessException;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryCustom {
  List<Role> getRolesByIdInAndAvailable(final Set<Long> ids, final Boolean available)
      throws BusinessException;
}
