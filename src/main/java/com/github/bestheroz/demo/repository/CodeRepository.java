package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.Code;
import com.github.bestheroz.demo.repository.custom.CodeRepositoryCustom;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long>, CodeRepositoryCustom {
  List<Code> findAllByTypeOrderByDisplayOrderAsc(String type);
}
