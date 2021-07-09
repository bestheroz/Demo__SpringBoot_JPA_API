package com.github.bestheroz.demo.api.internal.admin.code;

import com.github.bestheroz.demo.entity.code.CodeEntity;
import com.github.bestheroz.demo.entity.code.CodeRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

@Slf4j
@RestController
@RequestMapping(value = "api/admin/codes/")
public class AdminCodeController {
  @Resource private CodeRepository codeRepository;

  @GetMapping("types/")
  ResponseEntity<ApiResult> getTypes() {
    return Result.ok(this.codeRepository.getTypes());
  }

  @GetMapping
  ResponseEntity<ApiResult> getItems(@RequestParam(value = "type") final String type) {
    return Result.ok(this.codeRepository.findAllByTypeOrderByDisplayOrderAsc(type));
  }

  @PostMapping()
  public ResponseEntity<ApiResult> post(@RequestBody final CodeEntity payload) {
    return Result.created(this.codeRepository.save(payload));
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<ApiResult> put(
      @PathVariable(value = "id") final Long id, @RequestBody final CodeEntity payload) {
    return Result.ok(
        this.codeRepository
            .findById(id)
            .map(
                (item) -> {
                  BeanUtils.copyProperties(payload, item);
                  return this.codeRepository.save(item);
                })
            .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult> delete(@PathVariable(value = "id") final Long id) {
    return Result.ok(
        this.codeRepository
            .findById(id)
            .map(
                (item) -> {
                  this.codeRepository.delete(item);
                  return item;
                })
            .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS));
  }

  @PostMapping(value = "save")
  public ResponseEntity<ApiResult> save(@RequestBody final List<CodeEntity> payload) {
    return Result.created(
        payload.stream().map(code -> this.codeRepository.save(code)).collect(Collectors.toList()));
  }
}
