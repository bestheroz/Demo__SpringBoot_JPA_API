package com.github.bestheroz.standard.context.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class StaticConfig {
  private final Environment environment;

  public static String ACTIVE_PROFILE = null;
  public static Boolean LOCAL_ACTIVE_PROFILE_FLAG = null;

  @Autowired
  public void setConstant() {
    if (ACTIVE_PROFILE == null) {
      ACTIVE_PROFILE = this.environment.getActiveProfiles()[0];
    }
    if (LOCAL_ACTIVE_PROFILE_FLAG == null) {
      LOCAL_ACTIVE_PROFILE_FLAG = ACTIVE_PROFILE.equals("local");
    }
  }
}
