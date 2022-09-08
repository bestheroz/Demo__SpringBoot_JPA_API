package com.github.bestheroz.demo.domain;

import com.github.bestheroz.demo.api.internal.code.CodeDTO;
import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Code extends BaseAuditEntity implements Serializable {

  @Serial private static final long serialVersionUID = -6076508411557466173L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String type;

  @Column(name = "`value`")
  private String value;
  private String text;
  private Boolean availableFlag;
  private Integer displayOrder;

  public void change(final CodeDTO dto) {
    this.type = dto.getType();
    this.value = dto.getValue();
    this.text = dto.getText();
    this.availableFlag = dto.getAvailableFlag();
    this.displayOrder = dto.getDisplayOrder();
  }
}
