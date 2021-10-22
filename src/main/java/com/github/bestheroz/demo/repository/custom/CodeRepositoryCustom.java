package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.api.internal.code.CodeVO;
import java.util.List;

public interface CodeRepositoryCustom {
  List<String> getTypesByAvailable(Boolean available);

  List<CodeVO<String>> getCodesByTypeAndAvailableOrderByDisplayOrder(
      String type, final Boolean available);
}
