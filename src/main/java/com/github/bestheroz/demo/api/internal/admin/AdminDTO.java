package com.github.bestheroz.demo.api.internal.admin;

import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
import com.github.bestheroz.demo.domain.Admin;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDTO {

  private Long id;
  private String loginId;
  private String name;
  @NotNull private RoleSimpleDTO role;
  private Boolean availableFlag;
  private Instant expired;

  private Long createdBy;
  private Instant created;

  private Long updatedBy;
  private Instant updated;

  public AdminDTO(final Admin admin) {
    this.id = admin.getId();
    this.loginId = admin.getLoginId();
    this.name = admin.getName();
    this.role = new RoleSimpleDTO(admin.getRole());
    this.availableFlag = admin.getAvailableFlag();
    this.expired = admin.getExpired();
    this.created = admin.getCreated();
    this.createdBy = admin.getCreatedBy();
    this.updated = admin.getUpdated();
    this.updatedBy = admin.getUpdatedBy();
  }
}
