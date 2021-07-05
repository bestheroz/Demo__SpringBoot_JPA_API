package com.github.bestheroz.demo.entity.code;

import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
