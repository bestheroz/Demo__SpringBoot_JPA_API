package com.github.bestheroz.demo.api.internal.menu;

import com.github.bestheroz.demo.api.internal.role.menu.RoleMenuChildrenDTO;
import com.github.bestheroz.demo.domain.Menu;
import com.github.bestheroz.demo.helper.recursive.RecursiveEntityHelper;
import com.github.bestheroz.demo.repository.MenuRepository;
import com.github.bestheroz.demo.type.MenuType;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
  private final EntityManager entityManager;

  private final MenuRepository menuRepository;

  private Menu persist(final Menu menu) {
    this.entityManager.persist(menu);
    return menu;
  }

  public MenuChildrenDTO persist(final MenuSimpleDTO dto) {
    return new MenuChildrenDTO(this.persist(dto.toMenu(1000)));
  }

  @Transactional(readOnly = true)
  public List<MenuChildrenDTO> getItems() {
    return this.menuRepository
        .findAllByParentIdNullOrderByDisplayOrderAsc()
        .map(MenuChildrenDTO::new)
        .toList();
  }

  public void deleteById(final Long id) {
    this.menuRepository.deleteById(id);
  }

  public List<MenuChildrenDTO> saveAll(final List<MenuChildrenDTO> dtos) {
    // 조회
    final List<Menu> oldEntities =
        this.menuRepository.findAllByParentIdNullOrderByDisplayOrderAsc().toList();

    final RecursiveEntityHelper<Menu, MenuChildrenDTO> helper = new RecursiveEntityHelper<>();

    // 이동된 root 엔티티 삭제
    final List<Menu> entities = helper.deleteAndGetRemains(oldEntities, dtos, this.menuRepository);
    // 모두 저장
    return helper
        .saveAll(entities, dtos, this.entityManager, null, null)
        .map(MenuChildrenDTO::new)
        .toList();
  }

  public List<MenuChildrenDTO> getMenuChildrenDTOWithRecursiveChildren(
      final List<RoleMenuChildrenDTO> roleMenuChildrenDTOs) {
    final List<MenuChildrenDTO> result = new ArrayList<>();
    for (final RoleMenuChildrenDTO roleMenuChildrenDTO : roleMenuChildrenDTOs) {
      final MenuChildrenDTO menuChildrenDTO = roleMenuChildrenDTO.getMenu().toMenuChildrenDTO();
      if (menuChildrenDTO.getType().equals(MenuType.GROUP)) {
        menuChildrenDTO
            .getChildren()
            .addAll(
                this.getMenuChildrenDTOWithRecursiveChildren(roleMenuChildrenDTO.getChildren()));
      }
      result.add(menuChildrenDTO);
    }
    return result;
  }

  public MenuChildrenDTO put(final Long id, final MenuSimpleDTO payload) {
    return this.menuRepository
        .findById(id)
        .map((item) -> new MenuChildrenDTO(item.change(payload)))
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS));
  }
}
