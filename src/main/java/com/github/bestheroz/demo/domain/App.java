package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.type.AppPlatformType;
import java.io.Serializable;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "app")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class App extends AbstractCreatedUpdate implements Serializable {
  private static final long serialVersionUID = 7280716056600887400L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private AppPlatformType platform;

  private String description;
  private Boolean available;

  public void changeApp(
      final String name,
      final AppPlatformType platform,
      final String description,
      final Boolean available) {
    this.name = name;
    this.platform = platform;
    this.description = description;
    this.available = available;
  }
}
