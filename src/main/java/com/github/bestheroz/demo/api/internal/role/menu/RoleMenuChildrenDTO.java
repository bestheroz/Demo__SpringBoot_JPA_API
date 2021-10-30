package com.github.bestheroz.demo.api.internal.role.menu;

import com.github.bestheroz.demo.api.internal.menu.MenuChildrenDTO;
import com.github.bestheroz.demo.api.internal.menu.MenuSimpleDTO;
import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
import com.github.bestheroz.demo.domain.RoleMenuMap;
import com.github.bestheroz.demo.type.RoleAuthorityType;
import com.github.bestheroz.standard.common.util.NullUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMenuChildrenDTO {
  private Long id;
  @NotNull private MenuSimpleDTO menu;

  @NotNull private final Set<RoleAuthorityType> authoritiesJson = new HashSet<>();
  @NotNull private final List<RoleMenuChildrenDTO> children = new ArrayList<>();

  public RoleMenuChildrenDTO(final MenuChildrenDTO menuChildrenDTO) {
    this.menu = new MenuSimpleDTO(menuChildrenDTO);
    this.authoritiesJson.addAll(
        Set.of(
            RoleAuthorityType.VIEW,
            RoleAuthorityType.EXCEL,
            RoleAuthorityType.WRITE,
            RoleAuthorityType.DELETE));
    if (NullUtils.isNotEmpty(menuChildrenDTO.getChildren())) {
      this.children.addAll(
          menuChildrenDTO.getChildren().stream().map(RoleMenuChildrenDTO::new).toList());
    }
  }

  public RoleMenuChildrenDTO(final RoleMenuMap roleMenuMap) {
    this.id = roleMenuMap.getId();
    this.menu = new MenuSimpleDTO(roleMenuMap.getMenu());
    this.authoritiesJson.addAll(roleMenuMap.getAuthoritiesJson());
    if (NullUtils.isNotEmpty(roleMenuMap.getChildren())) {
      this.children.addAll(
          roleMenuMap.getChildren().stream().map(RoleMenuChildrenDTO::new).toList());
    }
  }

  public RoleMenuMap toRoleMenuMap(
      final RoleMenuMap parent, final Long roleId, final Integer displayOrder) {
    return RoleMenuMap.builder()
        .id(this.id)
        .authoritiesJson(this.authoritiesJson)
        .menu(this.menu.toMenu())
        .parent(parent)
        .role(new RoleSimpleDTO(roleId).toRole())
        .displayOrder(displayOrder)
        .build();
  }
}
