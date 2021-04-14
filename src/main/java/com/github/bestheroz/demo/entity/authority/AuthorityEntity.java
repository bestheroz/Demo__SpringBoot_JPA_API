package com.github.bestheroz.demo.entity.authority;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.bestheroz.demo.entity.AbstractCreatedUpdateEntity;
import com.github.bestheroz.demo.entity.authority.item.AuthorityItemEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "authority")
public class AuthorityEntity extends AbstractCreatedUpdateEntity implements Serializable {
  private static final long serialVersionUID = 6518292219807880047L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;
  private String name;

  @OneToMany(
      mappedBy = "authority",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  @JsonManagedReference
  @OrderBy("displayOrder ASC")
  private List<AuthorityItemEntity> items;
}
