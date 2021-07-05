package com.github.bestheroz.demo.api.internal.admin.menu;

import com.github.bestheroz.demo.entity.authority.item.AuthorityItemRepository;
import com.github.bestheroz.demo.entity.menu.MenuEntity;
import com.github.bestheroz.demo.entity.menu.MenuRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminMenuService {
  @Resource private MenuRepository menuRepository;
  @Resource private AuthorityItemRepository authorityItemRepository;

  public MenuEntity put(final MenuEntity payload, final Long id) {
    return this.menuRepository
        .findById(id)
        .map((item) -> this.menuRepository.save(item))
        .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS);
  }

  public MenuEntity delete(final Long id) {
    return this.menuRepository
        .findById(id)
        .map(
            (item) -> {
              this.menuRepository.delete(item);
              this.authorityItemRepository.deleteByMenu(item);
              return item;
            })
        .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS);
  }
}
