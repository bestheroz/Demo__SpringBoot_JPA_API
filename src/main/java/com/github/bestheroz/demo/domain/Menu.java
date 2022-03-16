package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.menu.MenuChildrenDTO;
import com.github.bestheroz.demo.api.internal.menu.MenuSimpleDTO;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntity;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntityChildrenHelper;
import com.github.bestheroz.demo.type.MenuType;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Builder
@Entity(name = "menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu extends RecursiveEntity<Menu, MenuChildrenDTO> implements Serializable {

  @Serial private static final long serialVersionUID = 2658557582464222508L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(referencedColumnName = "id")
  private Menu parent;

  @OneToMany(
      mappedBy = "parent",
      fetch = FetchType.LAZY,
      orphanRemoval = true,
      cascade = CascadeType.ALL)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<Menu> children = new ArrayList<>();

  private String name;

  @Enumerated(EnumType.STRING)
  private MenuType type;

  private Integer displayOrder;
  private String url;
  private String icon;

  @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @Builder.Default
  private final List<RoleMenuMap> roleMenuMaps = new ArrayList<>();

  @Column(updatable = false)
  @CreatedBy
  protected Long createdBy;

  @CreatedBy protected Long updatedBy;

  @Column(updatable = false)
  @CreatedDate
  protected Instant created;

  @CreatedDate protected Instant updated;

  public void change(final MenuSimpleDTO dto) {
    this.name = dto.getName();
    this.type = dto.getType();
    this.url = dto.getUrl();
    this.icon = dto.getIcon();
    this.updatedBy = AuthenticationUtils.getId();
    this.updated = Instant.now();
  }

  @Override
  public Menu change(final MenuChildrenDTO dto, final Menu parent) {
    this.name = dto.getName();
    this.type = dto.getType();
    this.url = dto.getUrl();
    this.icon = dto.getIcon();
    this.parent = parent;

    final List<MenuChildrenDTO> childrenDTOs = dto.getChildren();

    final RecursiveEntityChildrenHelper<Menu, MenuChildrenDTO> helper =
        new RecursiveEntityChildrenHelper<>();

    helper.deleteAndGetRemains(this.children, childrenDTOs);
    helper.saveAll(this.children, childrenDTOs, this, null);
    return this;
  }

  @Override
  public Menu setDisplayOrder(final Integer displayOrder) {
    this.displayOrder = displayOrder;
    return this;
  }
}
