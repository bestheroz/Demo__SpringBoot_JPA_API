package com.github.bestheroz.demo.api.internal.app;

import com.github.bestheroz.demo.type.AppPlatformType;
import java.util.List;
import lombok.Data;

@Data
public class AppFilter {
  private List<Boolean> availableList;
  private List<AppPlatformType> appPlatformTypeList;
}
