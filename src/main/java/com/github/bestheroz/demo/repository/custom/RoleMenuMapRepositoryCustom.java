package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.RoleMenuMap;
import java.util.stream.Stream;

public interface RoleMenuMapRepositoryCustom {
  Stream<RoleMenuMap> findAllByRoleIdAndParentIdNullOrderByDisplayOrderAscAndMenuInnerJoined(
      Long roleId);
}
