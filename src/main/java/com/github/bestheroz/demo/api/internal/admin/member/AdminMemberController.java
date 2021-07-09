package com.github.bestheroz.demo.api.internal.admin.member;

import com.github.bestheroz.demo.entity.member.MemberEntity;
import com.github.bestheroz.demo.entity.member.MemberRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.filter.DataTableSortDTO;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "api/admin/members/")
public class AdminMemberController {
  @Resource private MemberRepository memberRepository;

  @GetMapping
  ResponseEntity<ApiResult> getItems(
      final DataTableSortDTO dataTableSortDTO,
      final AdminMemberDTO adminMemberDTO,
      @RequestParam(required = false) final String search) {
    return Result.ok(
        this.memberRepository.findAllByDTO(
            search,
            adminMemberDTO.getAvailableList(),
            adminMemberDTO.getAuthorityList(),
            dataTableSortDTO.getPageRequest()));
  }

  @GetMapping(value = "{id}")
  ResponseEntity<ApiResult> getItem(@PathVariable(value = "id") final Long id) {
    return Result.ok(
        this.memberRepository
            .findById(id)
            .map(
                item -> {
                  item.setPassword(null);
                  return item;
                }));
  }

  @PostMapping
  public ResponseEntity<ApiResult> post(@RequestBody final MemberEntity payload) {
    return Result.created(this.memberRepository.save(payload));
  }

  @PatchMapping(value = "{id}")
  public ResponseEntity<ApiResult> patch(
      @PathVariable(value = "id") final Long id, @RequestBody final MemberEntity payload) {
    return Result.ok(
        this.memberRepository
            .findById(id)
            .map(
                (item) -> {
                  item.setName(payload.getName());
                  item.setAuthority(payload.getAuthority());
                  item.setExpired(payload.getExpired());
                  item.setAvailable(payload.getAvailable());
                  return this.memberRepository.save(item);
                })
            .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult> delete(@PathVariable(value = "id") final Long id) {
    return Result.ok(
        this.memberRepository
            .findById(id)
            .map(
                (item) -> {
                  this.memberRepository.delete(item);
                  return item;
                })
            .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS));
  }

  @PostMapping(value = "{id}/resetPassword")
  public ResponseEntity<ApiResult> resetPassword(@PathVariable(value = "id") final Long id) {
    return Result.ok(
        this.memberRepository
            .findById(id)
            .map(
                (item) -> {
                  item.setPassword(null);
                  item.setLoginFailCnt(0);
                  return this.memberRepository.save(item);
                })
            .orElseThrow(() -> BusinessException.FAIL_NO_DATA_SUCCESS));
  }
}
