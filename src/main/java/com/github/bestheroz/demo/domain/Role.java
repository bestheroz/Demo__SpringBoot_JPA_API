package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.role.RoleChildrenDTO;
import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntity;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntityChildrenHelper;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Entity(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Role extends RecursiveEntity<Role, RoleChildrenDTO> implements Serializable {
  @Serial private static final long serialVersionUID = 8475626710152801949L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(referencedColumnName = "id")
  private Role parent;

  @OneToMany(
      mappedBy = "parent",
      fetch = FetchType.LAZY,
      orphanRemoval = true,
      cascade = CascadeType.ALL)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<Role> children = new ArrayList<>();

  @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<RoleMenuMap> maps = new ArrayList<>();

  @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @Builder.Default
  private final List<Admin> admins = new ArrayList<>();

  private String name;
  private Boolean available;
  private Integer displayOrder;

  public Role change(final RoleSimpleDTO dto) {
    this.name = dto.getName();
    this.available = dto.getAvailable();
    return this;
  }

  @Override
  public Role change(final RoleChildrenDTO dto, final Role parent) {
    this.name = dto.getName();
    this.available = dto.getAvailable();
    this.parent = parent;

    final List<RoleChildrenDTO> childrenDTOs = dto.getChildren();
    final RecursiveEntityChildrenHelper<Role, RoleChildrenDTO> helper =
        new RecursiveEntityChildrenHelper<>();
    helper.deleteAndGetRemains(this.children, childrenDTOs);
    helper.saveAll(this.children, childrenDTOs, this, null);
    return this;
  }

  @Override
  public Role setDisplayOrder(final Integer displayOrder) {
    this.displayOrder = displayOrder;
    return this;
  }
}
