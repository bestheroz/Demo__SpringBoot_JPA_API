package com.github.bestheroz.standard.common.code;

import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/codes/")
public class CodeController {
  @Resource private CodeService codeService;

  @GetMapping()
  public ResponseEntity<ApiResult> getCodesByType(@RequestParam(value = "type") final String type) {
    return Result.ok(this.codeService.getCodesByTypeByAuthority(type));
  }
}
