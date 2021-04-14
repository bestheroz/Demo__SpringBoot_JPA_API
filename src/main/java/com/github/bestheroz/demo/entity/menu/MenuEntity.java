package com.github.bestheroz.demo.entity.menu;

import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import java.io.Serializable;
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
@Entity(name = "menu")
public class MenuEntity extends AbstractCreatedUpdateEntity implements Serializable {
  private static final long serialVersionUID = 2658557582464222508L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String type;
  private Long parentId;
  private Integer displayOrder;
  private String url;
  private String icon;
}
