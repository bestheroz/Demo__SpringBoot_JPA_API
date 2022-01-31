package com.github.bestheroz.standard.context.init;

import com.github.bestheroz.standard.common.util.AccessBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
public class StaticConfig {
  public static Boolean LOCAL_ACTIVE_PROFILE_FLAG = null;

  @Autowired
  public void setConstant() {
    if (LOCAL_ACTIVE_PROFILE_FLAG == null) {
      LOCAL_ACTIVE_PROFILE_FLAG =
          AccessBeanUtils.getBean(Environment.class).getActiveProfiles()[0].equals("local");
    }
  }
}
