package com.github.bestheroz.demo.repository.custom;

import com.github.bestheroz.demo.api.internal.admin.AdminDTO;
import com.github.bestheroz.demo.api.internal.code.CodeVO;
import com.github.bestheroz.standard.common.exception.BusinessException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositoryCustom {

  Page<AdminDTO> getAllByPaginationAndSearch(
      String search, List<Boolean> availableFlagList, List<Long> roleIdList, Pageable pageable);

  List<CodeVO<Long>> getCodeVOs() throws BusinessException;
}
