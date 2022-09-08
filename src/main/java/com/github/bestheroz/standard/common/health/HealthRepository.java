package com.github.bestheroz.standard.common.health;

import com.github.bestheroz.demo.domain.Code;
import com.github.bestheroz.demo.repository.custom.CodeRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends CrudRepository<Code, Long>, CodeRepositoryCustom {

  @Query(value = "select now()", nativeQuery = true)
  void selectNow();
}
