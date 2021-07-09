package com.github.bestheroz.demo.api.internal.admin.member;

import java.util.List;
import lombok.Data;

@Data
public class AdminMemberDTO {
  private List<Boolean> availableList;
  private List<String> authorityList;
}
