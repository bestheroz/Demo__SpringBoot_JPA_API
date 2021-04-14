package com.github.bestheroz.demo.entity;

import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.time.Instant;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AbstractCreatedUpdateEntity {
  protected String createdBy;
  @CreationTimestamp protected Instant created;
  protected String updatedBy;
  @UpdateTimestamp protected Instant updated;

  @PrePersist
  protected void onCreate() {
    this.updated = this.created = null;
    if (AuthenticationUtils.isLoggedIn()) {
      this.updatedBy = this.createdBy = AuthenticationUtils.getUserId();
    }
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated = null;
    if (AuthenticationUtils.isLoggedIn()) {
      this.updatedBy = AuthenticationUtils.getUserId();
    }
  }
}
