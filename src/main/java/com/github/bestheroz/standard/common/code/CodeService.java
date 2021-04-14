package com.github.bestheroz.standard.common.code;

import com.github.bestheroz.demo.entity.code.CodeRepository;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CodeService {
  @Resource private CodeRepository codeRepository;

  public List<CodeVO<String>> getCodesByTypeByAuthority(final String type) {
    final Long authorityId = AuthenticationUtils.getLoginVO().getAuthorityId();
    if (authorityId.equals(1L)) {
      return this.codeRepository.getCodesByType(type);
    } else {
      return this.codeRepository.getCodesByTypeAndAuthorityId(type, authorityId);
    }
  }
}
