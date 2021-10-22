package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
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
public class Role extends AbstractCreatedUpdate implements Serializable {
  private static final long serialVersionUID = 8475626710152801949L;

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
      cascade = CascadeType.MERGE)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<Role> children = new ArrayList<>();

  @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, orphanRemoval = true)
  @OrderBy("displayOrder ASC")
  @Builder.Default
  private final List<RoleMenuMap> maps = new ArrayList<>();

  private String name;
  private Boolean available;
  private Integer displayOrder;

  public void change(final RoleSimpleDTO dto) {
    this.name = dto.getName();
    this.available = dto.getAvailable();
  }
}
