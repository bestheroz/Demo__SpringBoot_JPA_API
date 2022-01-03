package com.github.bestheroz.demo.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditEntity extends BaseAuditTimeEntity {
  @Column(updatable = false)
  @CreatedBy
  protected Long createdBy;

  @LastModifiedBy protected Long updatedBy;
}
