package com.github.bestheroz.demo.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditTimeEntity {
  @Column(updatable = false)
  @CreatedDate
  protected Instant created;

  @LastModifiedDate protected Instant updated;
}
