package com.github.bestheroz.demo.entity.code.authority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import com.github.bestheroz.demo.entity.code.CodeEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "code_authority")
public class CodeAuthorityEntity extends AbstractCreatedUpdateEntity implements Serializable {
  private static final long serialVersionUID = 6518292219807880047L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long authorityId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private CodeEntity code;
}
