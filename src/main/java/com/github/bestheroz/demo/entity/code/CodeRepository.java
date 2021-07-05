package com.github.bestheroz.demo.entity.code;

import com.github.bestheroz.standard.common.code.CodeVO;
import com.github.bestheroz.standard.common.exception.BusinessException;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<CodeEntity, Long> {
  @Query(value = "select distinct c.type from code c order by c.type asc", nativeQuery = true)
  List<String> getTypes();

  List<CodeEntity> findAllByTypeOrderByDisplayOrderAsc(String type);

  @Query(
      value =
          "select new com.github.bestheroz.standard.common.code.CodeVO(c.value, c.name) "
              + "from code c where c.type = :type order by c.displayOrder asc")
  List<CodeVO<String>> getCodesByTypeOrderByDisplayOrder(String type);

  @Query(
      value =
          "select new com.github.bestheroz.standard.common.code.CodeVO(m.userId, m.name) "
              + "from member m order by m.id asc")
  List<CodeVO<String>> getMembers() throws BusinessException;
}
