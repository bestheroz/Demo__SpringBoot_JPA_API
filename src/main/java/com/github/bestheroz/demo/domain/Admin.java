package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.admin.AdminDTO;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Admin implements Serializable {
  private static final long serialVersionUID = 7280716056600887400L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String adminId;

  private String password;
  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Role role;

  private Integer signInFailCnt;
  private Boolean available;
  private String token;
  private Instant expired;

  @Column(updatable = false)
  protected Long createdBy;

  @Column(updatable = false)
  protected Instant created;

  @PrePersist
  protected void onCreate() {
    this.updated = this.created = Instant.now();
    if (AuthenticationUtils.isSigned()) {
      this.updatedBy = this.createdBy = AuthenticationUtils.getId();
    } else {
      this.updatedBy = this.createdBy = 0L;
    }
  }

  protected Long updatedBy;
  protected Instant updated;

  public void plusSignInFailCnt() {
    this.signInFailCnt = this.signInFailCnt + 1;
  }

  public void resetSignInFailCnt() {
    this.signInFailCnt = 0;
  }

  public void signedSuccess(final String token) {
    this.token = token;
    this.resetSignInFailCnt();
  }

  public void signOut() {
    this.token = null;
  }

  public void setPassword(final String password) {
    this.password = password;
    this.updated = Instant.now();
    this.updatedBy = AuthenticationUtils.getId();
    this.resetSignInFailCnt();
  }

  public void setName(final String name) {
    this.name = name;
    this.updated = Instant.now();
    this.updatedBy = AuthenticationUtils.getId();
  }

  public void change(final AdminDTO dto) {
    this.name = dto.getName();
    this.role = dto.getRole().toRole();
    this.available = dto.getAvailable();
    this.expired = dto.getExpired();
    this.updated = Instant.now();
    this.updatedBy = AuthenticationUtils.getId();
  }
}
