package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.domain.App;
import com.github.bestheroz.demo.type.AppPlatformType;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepositoryCustom {
  Page<App> findAllBySearch(
      String search,
      List<Boolean> availableList,
      List<AppPlatformType> appPlatformTypeList,
      Pageable pageable);
}
