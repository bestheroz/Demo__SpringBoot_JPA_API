package com.github.bestheroz.demo.api.internal.admin.authority;

import com.github.bestheroz.demo.entity.authority.AuthorityEntity;
import com.github.bestheroz.demo.entity.authority.AuthorityRepository;
import com.github.bestheroz.demo.entity.authority.item.AuthorityItemEntity;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AdminAuthorityService {
  @Resource private AuthorityRepository authorityRepository;

  @Transactional
  public List<AuthorityEntity> getItems() {
    return this.authorityRepository.findAllByOrderByNameAsc();
  }

  @Transactional
  public AuthorityEntity save(final AuthorityEntity payload) {
    for (final AuthorityItemEntity entity : payload.getItems()) {
      entity.setAuthority(payload);
    }
    return this.authorityRepository.save(payload);
  }
}
