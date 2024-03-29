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
public class AdminPasswordDTO {
  private Long id;
  private String loginId;
  private String name;
  private String password;
  @NotNull private RoleSimpleDTO role;
  private Boolean availableFlag;
  private Instant expired;

  private Long createdBy;
  private Instant created;

  private Long updatedBy;
  private Instant updated;

  public Admin toAdmin() {
    return Admin.builder()
        .loginId(this.loginId)
        .name(this.name)
        .password(this.password)
        .role(this.role.toRole())
        .availableFlag(this.availableFlag)
        .expired(this.expired)
        .signInFailCnt(0)
        .deleted(false)
        .build();
  }
}
