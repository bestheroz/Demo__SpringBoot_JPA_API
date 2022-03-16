package com.github.bestheroz.standard.common.dto;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DisplayOrderDTO {
  @NotNull private Long id;
  @NotNull private Integer displayOrder;
}
