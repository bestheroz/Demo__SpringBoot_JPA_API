package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.admin.AdminDTO;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@Entity(name = "admin")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Admin implements Serializable {

  @Serial private static final long serialVersionUID = 7280716056600887400L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String loginId;

  private String password;
  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Role role;

  private Integer signInFailCnt;
  private Boolean availableFlag;
  private String token;
  private Instant expired;
  private Boolean deleted;

  @CreatedBy
  @Column(updatable = false)
  protected Long createdBy;

  @CreatedDate
  @Column(updatable = false)
  protected Instant created;

  @CreatedBy protected Long updatedBy;

  @CreatedDate protected Instant updated;

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

  public Admin changePassword(final String password) {
    this.password = password;
    this.updated = Instant.now();
    this.updatedBy = AuthenticationUtils.getId();
    this.resetSignInFailCnt();
    return this;
  }

  public Admin changeName(final String name) {
    this.name = name;
    this.updated = Instant.now();
    this.updatedBy = AuthenticationUtils.getId();
    return this;
  }

  public Admin change(final AdminDTO dto) {
    this.loginId = dto.getLoginId();
    this.name = dto.getName();
    this.role = dto.getRole().toRole();
    this.availableFlag = dto.getAvailableFlag();
    this.expired = dto.getExpired();
    this.updated = Instant.now();
    this.updatedBy = AuthenticationUtils.getId();
    return this;
  }

  public Admin remove() {
    this.availableFlag = false;
    this.deleted = true;
    this.updated = Instant.now();
    this.updatedBy = AuthenticationUtils.getId();
    return this;
  }

  public void setToken(final String token) {
    this.token = token;
  }
}
