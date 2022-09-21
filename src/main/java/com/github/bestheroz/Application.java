package com.github.bestheroz;

import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication(
    scanBasePackages = "com.github.bestheroz",
    exclude = {UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public AuditorAware<Long> auditorProvider() {
    return () -> Optional.of(AuthenticationUtils.isSigned() ? AuthenticationUtils.getId() : 0L);
  }
}
