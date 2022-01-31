package com.github.bestheroz.demo.api.internal.role.menu;

import com.github.bestheroz.demo.api.internal.menu.MenuChildrenDTO;
import com.github.bestheroz.demo.api.internal.menu.MenuSimpleDTO;
import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
import com.github.bestheroz.demo.domain.RoleMenuMap;
import com.github.bestheroz.demo.helper.recursive.RecursiveDTO;
import com.github.bestheroz.demo.type.RoleAuthorityType;
import com.github.bestheroz.standard.common.util.NullUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMenuChildrenDTO extends RecursiveDTO<RoleMenuChildrenDTO, RoleMenuMap> {
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

  public RoleMenuMap toRoleMenuMap(final RoleMenuMap parent, final Long roleId) {
    final RoleMenuMap build =
        RoleMenuMap.builder()
            .id(this.id)
            .authoritiesJson(this.authoritiesJson)
            .menu(this.menu.toMenu())
            .parent(parent)
            .displayOrder(999_999)
            .role(new RoleSimpleDTO(roleId).toRole())
            .build();

    build
        .getChildren()
        .addAll(this.children.stream().map(child -> child.toRoleMenuMap(build, roleId)).toList());

    return build;
  }

  @Override
  public RoleMenuMap toEntity(final RoleMenuMap parent, final Long roleId) {
    return this.toRoleMenuMap(parent, roleId);
  }

  @Override
  public void setIdNull() {
    this.id = null;
    this.children.forEach(RoleMenuChildrenDTO::setIdNull);
  }
}
