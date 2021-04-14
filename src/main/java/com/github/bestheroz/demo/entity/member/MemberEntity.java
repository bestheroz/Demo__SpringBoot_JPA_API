package com.github.bestheroz.demo.entity.member;

import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "member")
public class MemberEntity extends AbstractCreatedUpdateEntity implements Serializable {
  private static final long serialVersionUID = 7280716056600887400L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String userId;

  private String password;
  private String name;
  private Long authorityId;
  private Integer loginFailCnt;
  private Boolean available;
  private String theme;
  private String token;
  private Instant expired;
}
