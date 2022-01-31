package com.github.bestheroz.demo.api.internal.mine;

import com.github.bestheroz.demo.domain.Admin;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EditMeDTO {
  private Long id;
  private String name;
  private String password;

  public EditMeDTO(final Admin admin) {
    this.id = admin.getId();
    this.name = admin.getName();
  }
}
