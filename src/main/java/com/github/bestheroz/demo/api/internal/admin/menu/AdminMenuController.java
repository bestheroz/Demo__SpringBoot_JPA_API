package com.github.bestheroz.demo.api.internal.admin.menu;

import com.github.bestheroz.demo.entity.menu.MenuEntity;
import com.github.bestheroz.demo.entity.menu.MenuRepository;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/admin/menus/")
public class AdminMenuController {
  @Resource private AdminMenuService adminMenuService;
  @Resource private MenuRepository menuRepository;

  @GetMapping
  ResponseEntity<ApiResult> getItems() {
    return Result.ok(
        this.menuRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "displayOrder", "name")));
  }

  @PostMapping
  public ResponseEntity<ApiResult> post(@RequestBody final MenuEntity payload) {
    return Result.created(this.menuRepository.save(payload));
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<ApiResult> put(
      @PathVariable(value = "id") final Long id, @RequestBody final MenuEntity menuEntity) {
    return Result.ok(this.adminMenuService.put(menuEntity, id));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult> delete(@PathVariable(value = "id") final Long id) {
    return Result.ok(this.adminMenuService.delete(id));
  }

  @PostMapping(value = "save")
  public ResponseEntity<ApiResult> save(@RequestBody final List<MenuEntity> payload) {
    return Result.created(
        payload.stream().map(menu -> this.menuRepository.save(menu)).collect(Collectors.toList()));
  }
}
