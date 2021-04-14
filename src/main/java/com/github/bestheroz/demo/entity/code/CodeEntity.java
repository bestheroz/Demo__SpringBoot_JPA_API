package com.github.bestheroz.demo.entity.code;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import com.github.bestheroz.demo.entity.code.authority.CodeAuthorityEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity(name = "code")
@Getter
@Setter
@RequiredArgsConstructor
public class CodeEntity extends AbstractCreatedUpdateEntity implements Serializable {
  private static final long serialVersionUID = -6076508411557466173L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  @Column(unique = true)
  private String value;

  private String name;
  private Boolean available;
  private Integer displayOrder;

  @OneToMany(
      mappedBy = "code",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<CodeAuthorityEntity> authorities;
}
