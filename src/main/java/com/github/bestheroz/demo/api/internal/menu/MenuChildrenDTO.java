package com.github.bestheroz.demo.api.internal.menu;

import com.github.bestheroz.demo.domain.Menu;
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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MenuChildrenDTO {
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

  public Menu toMenu(final Menu parent, final Integer displayOrder) {
    return Menu.builder()
        .id(this.id)
        .name(this.name)
        .type(this.type)
        .parent(parent)
        .displayOrder(displayOrder)
        .url(this.url)
        .icon(this.icon)
        .build();
  }
}
