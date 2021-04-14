package com.github.bestheroz.demo.api.internal.member;

import com.github.bestheroz.demo.entity.authority.AuthorityEntity;
import com.github.bestheroz.demo.entity.authority.AuthorityRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MemberService {
  @Resource private AuthorityRepository authorityRepository;

  @Transactional
  public AuthorityEntity getItem(final Long id) {
    return this.authorityRepository
        .findById(id)
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER));
  }
}
