package com.github.bestheroz.demo.api.internal.role;

import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuChildrenDTO;
import com.github.bestheroz.demo.domain.Role;
import com.github.bestheroz.standard.common.util.NullUtils;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMapsDTO {
  private Long id;

  @NotEmpty private String name;

  @NotNull private Boolean available;

  @NotNull private final List<RoleMenuChildrenDTO> maps = new ArrayList<>();

  private Long createdBy;
  private Instant created;

  private Long updatedBy;
  private Instant updated;

  public RoleMapsDTO(final Role role) {
    this.id = role.getId();
    this.name = role.getName();
    this.available = role.getAvailable();
    if (NullUtils.isNotEmpty(role.getMaps())) {
      this.maps.addAll(
          role.getMaps().stream()
              .filter(m -> m.getParent() == null)
              .map(RoleMenuChildrenDTO::new)
              .collect(Collectors.toList()));
    }
    this.created = role.getCreated();
    this.createdBy = role.getCreatedBy();
    this.updated = role.getUpdated();
    this.updatedBy = role.getUpdatedBy();
  }
}
