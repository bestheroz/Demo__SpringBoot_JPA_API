package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.api.internal.code.CodeVO;
import com.github.bestheroz.demo.domain.Code;
import com.github.bestheroz.demo.repository.custom.CodeRepositoryCustom;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long>, CodeRepositoryCustom {

  Stream<Code> findAllByTypeOrderByDisplayOrderAsc(String type);

  List<CodeVO<String>> findCodeVOsByTypeAndAvailableOrderByDisplayOrder(
      String type, final Boolean available);

  @Modifying
  @Query("update code c set c.displayOrder = :displayOrder where c.id = :id")
  void updateDisplayOrderById(@Param("id") Long id, @Param("displayOrder") Integer displayOrder);
}
