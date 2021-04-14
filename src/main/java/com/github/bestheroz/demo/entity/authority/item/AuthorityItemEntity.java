package com.github.bestheroz.demo.entity.authority.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import com.github.bestheroz.demo.entity.authority.AuthorityEntity;
import com.github.bestheroz.demo.entity.menu.MenuEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "authority_item")
@TypeDef(name = "jsonString", typeClass = JsonStringType.class)
public class AuthorityItemEntity extends AbstractCreatedUpdateEntity implements Serializable {
  private static final long serialVersionUID = 6518292219807880047L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer displayOrder;

  @Type(type = "jsonString")
  private List<String> typesJson;

  @OneToOne
  @JoinColumn(name = "menuId", referencedColumnName = "id", nullable = false)
  private MenuEntity menu;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private AuthorityEntity authority;
}
