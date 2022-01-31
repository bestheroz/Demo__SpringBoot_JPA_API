package com.github.bestheroz.demo.api.internal.menu;

import com.github.bestheroz.demo.domain.Menu;
import com.github.bestheroz.demo.helper.recursive.RecursiveDTO;
import com.github.bestheroz.demo.type.MenuType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MenuChildrenDTO extends RecursiveDTO<MenuChildrenDTO, Menu> {
  private Long id;

  @NotEmpty private String name;

  @Enumerated(EnumType.STRING)
  private MenuType type;

  private String url;
  private String icon;

  @NotNull private final List<MenuChildrenDTO> children = new ArrayList<>();

  private Long createdBy;
  private Instant created;
  private Long updatedBy;
  private Instant updated;

  public MenuChildrenDTO(final Menu menu) {
    this.id = menu.getId();
    this.name = menu.getName();
    this.type = menu.getType();
    this.url = menu.getUrl();
    this.icon = menu.getIcon();
    if (menu.getType() != null && menu.getType().equals(MenuType.GROUP)) {
      this.children.addAll(menu.getChildren().stream().map(MenuChildrenDTO::new).toList());
    }
    this.created = menu.getCreated();
    this.createdBy = menu.getCreatedBy();
    this.updated = menu.getUpdated();
    this.updatedBy = menu.getUpdatedBy();
  }

  public Menu toMenu(final Menu parent) {
    final Menu build =
        Menu.builder()
            .id(this.id)
            .name(this.name)
            .type(this.type)
            .parent(parent)
            .displayOrder(999_999)
            .url(this.url)
            .icon(this.icon)
            .build();

    build.getChildren().addAll(this.children.stream().map(child -> child.toMenu(build)).toList());

    return build;
  }

  @Override
  public Menu toEntity(final Menu menu, final Long key) {
    return this.toMenu(menu);
  }

  @Override
  public void setIdNull() {
    this.id = null;
    this.children.forEach(MenuChildrenDTO::setIdNull);
  }
}
