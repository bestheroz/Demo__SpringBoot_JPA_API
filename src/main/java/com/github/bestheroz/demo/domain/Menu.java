package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.menu.MenuSimpleDTO;
import com.github.bestheroz.demo.type.MenuType;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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

@Getter
@Builder
@Entity(name = "menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu extends BaseAuditEntity implements Serializable {
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
      cascade = CascadeType.MERGE)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<Menu> children = new ArrayList<>();

  private String name;

  @Enumerated(EnumType.STRING)
  private MenuType type;

  private Integer displayOrder;
  private String url;
  private String icon;

  public void changeMenu(final MenuSimpleDTO dto) {
    this.id = dto.getId();
    this.name = dto.getName();
    this.type = dto.getType();
    this.url = dto.getUrl();
    this.icon = dto.getIcon();
  }
}
