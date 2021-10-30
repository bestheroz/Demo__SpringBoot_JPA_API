package com.github.bestheroz.demo.api.internal.code;

import com.github.bestheroz.demo.repository.CodeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {
  private final CodeRepository codeRepository;

  public List<CodeDTO> saveAll(final List<CodeDTO> payload) {
    return IterableUtils.toList(
            this.codeRepository.saveAll(payload.stream().map(CodeDTO::toCode).toList()))
        .stream()
        .map(CodeDTO::new)
        .toList();
  }
}
