package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.role.RoleChildrenDTO;
import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntity;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntityChildrenHelper;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@Entity(name = "role")
@EntityListeners(AuditingEntityListener.class)
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

  @OneToMany(
      mappedBy = "role",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
      orphanRemoval = true)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<RoleMenuMap> maps = new ArrayList<>();

  @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @Builder.Default
  private final List<Admin> admins = new ArrayList<>();

  private String name;
  private Boolean availableFlag;
  private Integer displayOrder;

  @Column(updatable = false)
  @CreatedBy
  protected Long createdBy;

  @CreatedBy protected Long updatedBy;

  @Column(updatable = false)
  @CreatedDate
  protected Instant created;

  @CreatedDate protected Instant updated;

  private Boolean deleted;

  public void change(final RoleSimpleDTO dto) {
    this.name = dto.getName();
    this.availableFlag = dto.getAvailableFlag();
    this.updatedBy = AuthenticationUtils.getId();
    this.updated = Instant.now();
  }

  @Override
  public Role change(final RoleChildrenDTO dto, final Role parent) {
    this.name = dto.getName();
    this.availableFlag = dto.getAvailableFlag();
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
