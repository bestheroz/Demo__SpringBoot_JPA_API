package com.github.bestheroz.demo.api.internal.code;

import com.github.bestheroz.demo.repository.CodeRepository;
import com.github.bestheroz.standard.common.dto.DisplayOrderDTO;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/codes/")
@RequiredArgsConstructor
public class CodeController {

  private final CodeRepository codeRepository;
  private final CodeService codeService;

  @GetMapping("types/")
  ResponseEntity<ApiResult<List<String>>> getTypes(
      @RequestParam(required = false) final Boolean availableFlag) {
    return Result.ok(this.codeRepository.getTypesByAvailableFlag(availableFlag));
  }

  @GetMapping
  ResponseEntity<ApiResult<List<CodeDTO>>> getItems(
      @RequestParam(value = "type") final String type) {
    return Result.ok(this.codeService.getItems(type));
  }

  @PostMapping
  public ResponseEntity<ApiResult<CodeDTO>> post(@RequestBody @Valid final CodeDTO payload) {
    return Result.created(this.codeService.persist(payload));
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<ApiResult<CodeDTO>> put(
      @PathVariable(value = "id") final Long id, @RequestBody @Valid final CodeDTO payload) {
    return Result.ok(this.codeService.change(id, payload));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult<?>> delete(@PathVariable(value = "id") final Long id) {
    this.codeRepository.deleteById(id);
    return Result.ok();
  }

  @PatchMapping(value = "display-orders/")
  public ResponseEntity<ApiResult<List<CodeDTO>>> patchDisplayOrder(
      @RequestParam final String type, @RequestBody @Valid final List<DisplayOrderDTO> payload) {
    return Result.created(this.codeService.patchDisplayOrders(type, payload));
  }
}
