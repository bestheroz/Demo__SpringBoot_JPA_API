package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.api.internal.code.CodeVO;
import com.github.bestheroz.demo.domain.Admin;
import com.github.bestheroz.standard.common.exception.BusinessException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositoryCustom {
  Page<Admin> findAllBySearch(
      String search, List<Boolean> availableList, List<Long> roleIdList, Pageable pageable);

  List<CodeVO<Long>> getCodes() throws BusinessException;
}
