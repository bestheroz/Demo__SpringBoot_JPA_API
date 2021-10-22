package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.RoleMenuMap;
import java.util.List;

public interface RoleMenuMapRepositoryCustom {
  List<RoleMenuMap> findAllByRoleIdAndParentIdNullOrderByDisplayOrderAscAndMenuInnerJoined(
      Long roleId);
}
