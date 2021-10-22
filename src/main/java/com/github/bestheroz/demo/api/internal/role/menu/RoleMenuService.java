package com.github.bestheroz.demo.api.internal.role.menu;

import com.github.bestheroz.demo.domain.RoleMenuMap;
import com.github.bestheroz.demo.repository.RoleMenuMapRepository;
import com.github.bestheroz.standard.common.util.NullUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoleMenuService {
  private final RoleMenuMapRepository roleMenuMapRepository;

  @Transactional(readOnly = true)
  public List<RoleMenuChildrenDTO> getItems(final Long roleId) {
    final List<RoleMenuMap> roleMenuMaps =
        this.roleMenuMapRepository
            .findAllByRoleIdAndParentIdNullOrderByDisplayOrderAscAndMenuInnerJoined(roleId);
    return roleMenuMaps.stream().map(RoleMenuChildrenDTO::new).collect(Collectors.toList());
  }

  public List<RoleMenuChildrenDTO> saveAll(
      final Long roleId, final List<RoleMenuChildrenDTO> payload) {
    if (NullUtils.isEmpty(payload)) {
      this.roleMenuMapRepository.deleteByRoleId(roleId);
    } else {
      this.roleMenuMapRepository.deleteByRoleIdAndIdNotInAndParentIdNull(
          roleId,
          payload.stream()
              .map(RoleMenuChildrenDTO::getId)
              .filter(Objects::nonNull)
              .collect(Collectors.toList()));
    }
    return IterableUtils.toList(
            this.roleMenuMapRepository.saveAll(
                this.getRoleMenuMapWithRecursiveChildren(roleId, payload, null)))
        .stream()
        .map(RoleMenuChildrenDTO::new)
        .collect(Collectors.toList());
  }

  public List<RoleMenuMap> getRoleMenuMapWithRecursiveChildren(
      final Long roleId,
      final List<RoleMenuChildrenDTO> roleMenuChildrenDTOS,
      final RoleMenuMap parent) {
    int displayOrder = 1;
    final List<RoleMenuMap> result = new ArrayList<>();
    for (final RoleMenuChildrenDTO roleMenuChildrenDTO : roleMenuChildrenDTOS) {
      final RoleMenuMap roleMenuMap =
          roleMenuChildrenDTO.toRoleMenuMap(parent, roleId, displayOrder++);
      if (NullUtils.isNotEmpty(roleMenuChildrenDTO.getChildren())) {
        roleMenuMap
            .getChildren()
            .addAll(
                this.getRoleMenuMapWithRecursiveChildren(
                    roleId, roleMenuChildrenDTO.getChildren(), roleMenuMap));
      }
      result.add(roleMenuMap);
    }
    return result;
  }
}
