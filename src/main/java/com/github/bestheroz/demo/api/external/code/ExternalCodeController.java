package com.github.bestheroz.demo.api.external.code;

import com.github.bestheroz.demo.entity.code.CodeRepository;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/api/codes/")
public class ExternalCodeController {
  @Resource private CodeRepository codeRepository;

  @GetMapping("types/")
  ResponseEntity<ApiResult> getTypes() {
    return Result.ok(this.codeRepository.getTypes());
  }

  @GetMapping()
  ResponseEntity<ApiResult> getItems(@RequestParam(value = "type") final String type) {
    return Result.ok(this.codeRepository.findAllByTypeOrderByDisplayOrderAsc(type));
  }
}
