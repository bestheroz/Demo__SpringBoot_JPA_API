package com.github.bestheroz.demo.api.internal.code;

import com.github.bestheroz.demo.domain.Code;
import com.github.bestheroz.demo.repository.CodeRepository;
import com.github.bestheroz.standard.common.dto.DisplayOrderDTO;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CodeService {

  private final EntityManager entityManager;
  private final CodeRepository codeRepository;

  private Code persist(final Code code) {
    this.entityManager.persist(code);
    return code;
  }

  public CodeDTO persist(final CodeDTO dto) {
    return new CodeDTO(this.persist(dto.toCode()));
  }

  @Transactional(readOnly = true)
  public List<CodeDTO> getItems(final String type) {
    return this.codeRepository.findAllByTypeOrderByDisplayOrderAsc(type).map(CodeDTO::new).toList();
  }

  public CodeDTO change(final Long id, final CodeDTO codeDTO) {
    return this.codeRepository
        .findById(id)
        .map(
            (code) -> {
              code.change(codeDTO);
              this.entityManager.flush();
              return new CodeDTO(code);
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }

  public List<CodeDTO> patchDisplayOrders(final String type, final List<DisplayOrderDTO> codeDTOS) {
    codeDTOS.forEach(
        c -> this.codeRepository.updateDisplayOrderById(c.getId(), c.getDisplayOrder()));
    return this.getItems(type);
  }
}
