package com.github.bestheroz.standard.common.code;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CodeVO<T> implements Serializable {
  private static final long serialVersionUID = 272726757907169621L;
  private T value;
  private String text;
}
