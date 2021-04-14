package com.github.bestheroz.demo.api.internal.admin.menu;

import com.github.bestheroz.demo.entity.authority.AuthorityRepository;
import com.github.bestheroz.demo.entity.menu.MenuEntity;
import com.github.bestheroz.demo.entity.menu.MenuRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminMenuService {
  @Resource private MenuRepository menuRepository;
  @Resource private AuthorityRepository authorityRepository;

  @Transactional
  public MenuEntity put(final MenuEntity payload, final Long id) {
    return this.menuRepository
        .findById(id)
        .map(
            (item) -> {
              BeanUtils.copyProperties(payload, item);
              this.authorityRepository
                  .findAllById(id)
                  .forEach(
                      authority -> {
                        BeanUtils.copyProperties(payload, authority);
                        this.authorityRepository.save(authority);
                      });
              return this.menuRepository.save(item);
            })
        .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS);
  }

  @Transactional
  public MenuEntity delete(final Long id) {
    return this.menuRepository
        .findById(id)
        .map(
            (item) -> {
              this.menuRepository.delete(item);
              this.authorityRepository.deleteAllById(id);
              return item;
            })
        .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS);
  }
}
