package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.mine.MineConfigDTO;
import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "admin_config")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminConfig extends AbstractCreatedUpdate implements Serializable {
  @Serial private static final long serialVersionUID = 1426310156205338408L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
  private Admin admin;

  private String globalTheme;
  private String toolbarTheme;
  private String menuTheme;
  private Boolean toolbarDetached;
  private Boolean contentBoxed;
  private String primaryColor;

  public void change(final MineConfigDTO dto) {
    this.globalTheme = dto.getGlobalTheme();
    this.toolbarTheme = dto.getToolbarTheme();
    this.menuTheme = dto.getMenuTheme();
    this.toolbarDetached = dto.getToolbarDetached();
    this.contentBoxed = dto.getContentBoxed();
    this.primaryColor = dto.getPrimaryColor();
  }
}
