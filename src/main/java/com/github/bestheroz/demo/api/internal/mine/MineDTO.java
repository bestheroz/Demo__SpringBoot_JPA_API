package com.github.bestheroz.demo.api.internal.mine;

import com.github.bestheroz.demo.api.internal.role.RoleSimpleDTO;
import com.github.bestheroz.demo.domain.Admin;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MineDTO {
  private Long id;
  private String loginId;
  private String name;
  private RoleSimpleDTO role;
  private Instant created;
  private Instant updated;

  public MineDTO(final Admin admin) {
    this.id = admin.getId();
    this.name = admin.getName();
    this.loginId = admin.getLoginId();
    this.role = new RoleSimpleDTO(admin.getRole());
    this.created = admin.getCreated();
    this.updated = admin.getUpdated();
  }
}
