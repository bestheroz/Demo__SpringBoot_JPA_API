package com.github.bestheroz.demo.entity.member.config;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import com.github.bestheroz.demo.entity.member.MemberEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "member_config")
public class MemberConfigEntity extends AbstractCreatedUpdateEntity implements Serializable {
  private static final long serialVersionUID = 1426310156205338408L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String globalTheme;
  private String menuTheme;
  private Boolean contentBoxed;
  private String primaryColor;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private MemberEntity member;
}
