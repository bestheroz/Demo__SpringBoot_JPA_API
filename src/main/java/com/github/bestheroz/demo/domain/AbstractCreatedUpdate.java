package com.github.bestheroz.demo.domain;

import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@MappedSuperclass
public abstract class AbstractCreatedUpdate {
  @Column(updatable = false)
  protected Long createdBy;

  @Column(updatable = false)
  @CreationTimestamp
  protected Instant created;

  protected Long updatedBy;
  @UpdateTimestamp protected Instant updated;

  @PrePersist
  protected void onCreate() {
    this.updated = this.created = null;
    if (AuthenticationUtils.isSigned()) {
      this.updatedBy = this.createdBy = AuthenticationUtils.getId();
    } else {
      this.updatedBy = this.createdBy = 0L;
    }
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated = null;
    if (AuthenticationUtils.isSigned()) {
      this.updatedBy = AuthenticationUtils.getId();
    } else {
      this.updatedBy = 0L;
    }
  }
}
