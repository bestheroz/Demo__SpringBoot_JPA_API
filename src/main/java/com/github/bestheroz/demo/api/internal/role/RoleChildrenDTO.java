package com.github.bestheroz.demo.api.internal.role;

import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.demo.helper.recursive.RecursiveDTO;
import com.github.bestheroz.standard.common.util.NullUtils;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleChildrenDTO extends RecursiveDTO<RoleChildrenDTO, Role> {
  private Long id;

  @NotEmpty private String name;

  @NotNull private Boolean available;

  @NotNull private final List<RoleChildrenDTO> children = new ArrayList<>();

  private Long createdBy;
  private Instant created;

  private Long updatedBy;
  private Instant updated;

  public RoleChildrenDTO(final Role role) {
    this.id = role.getId();
    this.name = role.getName();
    this.available = role.getAvailable();
    if (NullUtils.isNotEmpty(role.getChildren())) {
      this.children.addAll(
          role.getChildren().stream()
              .filter((e) -> !e.getDeleted())
              .map(RoleChildrenDTO::new)
              .toList());
    }
    this.created = role.getCreated();
    this.createdBy = role.getCreatedBy();
    this.updated = role.getUpdated();
    this.updatedBy = role.getUpdatedBy();
  }

  public Role toRole(final Role parent) {
    final Role build =
        Role.builder()
            .id(this.id)
            .name(this.name)
            .available(this.available)
            .parent(parent)
            .displayOrder(999_999)
            .deleted(false)
            .build();

    build.getChildren().addAll(this.children.stream().map(child -> child.toRole(build)).toList());

    return build;
  }

  @Override
  public Role toEntity(final Role parent, final Long key) {
    return this.toRole(parent);
  }

  @Override
  public void setIdNull() {
    this.id = null;
    this.children.forEach(RoleChildrenDTO::setIdNull);
  }
}
