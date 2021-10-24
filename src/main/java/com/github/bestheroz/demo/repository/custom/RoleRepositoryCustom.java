package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.standard.common.exception.BusinessException;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryCustom {
  List<Role> getRolesByIdInAndAvailable(final List<Long> ids, final Boolean available)
      throws BusinessException;
}
