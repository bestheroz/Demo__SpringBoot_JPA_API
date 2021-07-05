package com.github.bestheroz.demo.api.internal.admin.authority;

import com.github.bestheroz.demo.entity.authority.item.AuthorityItemEntity;
import com.github.bestheroz.demo.entity.authority.item.AuthorityItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AdminAuthorityService {
  @Resource private AuthorityItemRepository authorityItemRepository;

  public List<AuthorityItemEntity> save(
      final String authority, final List<AuthorityItemEntity> payload) {
    return payload.stream()
        .map(
            item -> {
              item.setAuthority(authority);
              return this.authorityItemRepository.save(item);
            })
        .collect(Collectors.toList());
  }
}
