package com.github.bestheroz.demo.repository.custom;

import java.util.List;

public interface CodeRepositoryCustom {
  List<String> getTypesByAvailable(Boolean available);
}
