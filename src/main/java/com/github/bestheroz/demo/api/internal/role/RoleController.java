package com.github.bestheroz.demo.api.internal.role;

import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/roles/")
@RequiredArgsConstructor
public class RoleController {
  private final RoleService roleService;

  @GetMapping(value = "selections/")
  public ResponseEntity<ApiResult<List<RoleSimpleDTO>>> getSelections(
      @RequestParam(required = false) final Boolean availableFlag) {
    return Result.ok(this.roleService.getSelections(availableFlag));
  }

  @GetMapping
  ResponseEntity<ApiResult<List<RoleChildrenDTO>>> getItems() {
    return Result.ok(
        this.roleService.getItems().stream().filter(v -> !v.getId().equals(1L)).toList());
  }

  @PostMapping()
  public ResponseEntity<ApiResult<RoleChildrenDTO>> post(
      @RequestParam(required = false) final Long parentId,
      @RequestBody @Valid final RoleSimpleDTO payload) {
    return Result.created(this.roleService.persist(parentId, payload));
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<ApiResult<RoleSimpleDTO>> put(
      @PathVariable(value = "id") final Long id, @RequestBody @Valid final RoleSimpleDTO payload) {
    return Result.ok(this.roleService.put(id, payload));
  }

  @PostMapping(value = "save-all/")
  public ResponseEntity<ApiResult<List<RoleChildrenDTO>>> saveAll(
      @RequestBody @Valid final List<RoleChildrenDTO> payload) {
    return Result.created(this.roleService.saveAll(payload));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult<Set<Long>>> delete(@PathVariable(value = "id") final Long id) {
    return Result.ok(this.roleService.delete(id));
  }
}
