package com.github.bestheroz.demo.api.internal.admin.authority;

import com.github.bestheroz.demo.entity.authority.item.AuthorityItemEntity;
import com.github.bestheroz.demo.entity.authority.item.AuthorityItemRepository;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/admin/authorities/")
@Slf4j
public class AdminAuthorityController {
  @Resource private AdminAuthorityService adminAuthorityService;
  @Resource private AuthorityItemRepository authorityItemRepository;

  @GetMapping("{authority}")
  ResponseEntity<ApiResult> getItems(@PathVariable("authority") final String authority) {
    return Result.ok(
        this.authorityItemRepository.findAllByAuthorityOrderByDisplayOrderAsc(authority));
  }

  @PostMapping(value = "{authority}")
  public ResponseEntity<ApiResult> save(
      @PathVariable("authority") final String authority,
      @RequestBody final List<AuthorityItemEntity> payload) {
    return Result.created(this.adminAuthorityService.save(authority, payload));
  }
}
