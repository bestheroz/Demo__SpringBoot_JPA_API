package com.github.bestheroz.demo.api.internal.mine;

import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangePasswordDTO {
  @NotEmpty private String oldPassword;
  @NotEmpty private String newPassword;
}
