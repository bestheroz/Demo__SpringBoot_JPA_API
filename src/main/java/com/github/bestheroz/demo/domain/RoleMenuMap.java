package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuChildrenDTO;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntity;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntityChildrenHelper;
import com.github.bestheroz.demo.type.RoleAuthorityType;
import com.github.bestheroz.standard.common.util.NullUtils;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@Entity(name = "role_menu_map")
@TypeDef(name = "jsonString", typeClass = JsonStringType.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class RoleMenuMap extends RecursiveEntity<RoleMenuMap, RoleMenuChildrenDTO>
    implements Serializable {
  @Serial private static final long serialVersionUID = -4753709861734048435L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(referencedColumnName = "id")
  private RoleMenuMap parent;

  @OneToMany(
      mappedBy = "parent",
      fetch = FetchType.LAZY,
      orphanRemoval = true,
      cascade = CascadeType.ALL)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<RoleMenuMap> children = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  private Role role;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(nullable = false)
  private Menu menu;

  private Integer displayOrder;

  @Type(type = "jsonString")
  @Builder.Default
  private final Set<RoleAuthorityType> authoritiesJson = new HashSet<>();

  @Column(updatable = false)
  @CreatedBy
  protected Long createdBy;

  @LastModifiedBy protected Long updatedBy;

  @Column(updatable = false)
  @CreatedDate
  protected Instant created;

  @LastModifiedDate protected Instant updated;

  @Override
  public RoleMenuMap change(final RoleMenuChildrenDTO dto, final RoleMenuMap parent) {
    this.menu = dto.getMenu().toMenu();
    this.authoritiesJson.clear();
    this.parent = parent;
    if (NullUtils.isNotEmpty(dto.getAuthoritiesJson())) {
      this.authoritiesJson.addAll(dto.getAuthoritiesJson());
    }

    final List<RoleMenuChildrenDTO> childrenDTOs = dto.getChildren();
    final RecursiveEntityChildrenHelper<RoleMenuMap, RoleMenuChildrenDTO> helper =
        new RecursiveEntityChildrenHelper<>();
    helper.deleteAndGetRemains(this.children, childrenDTOs);
    helper.saveAll(this.children, childrenDTOs, this, this.role.getId());
    return this;
  }

  @Override
  public RoleMenuMap setDisplayOrder(final Integer displayOrder) {
    this.displayOrder = displayOrder;
    return this;
  }
}
