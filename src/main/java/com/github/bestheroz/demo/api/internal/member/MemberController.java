package com.github.bestheroz.demo.api.internal.member;

import com.github.bestheroz.demo.entity.authority.item.AuthorityItemEntity;
import com.github.bestheroz.demo.entity.authority.item.AuthorityItemRepository;
import com.github.bestheroz.demo.entity.code.CodeRepository;
import com.github.bestheroz.demo.entity.member.MemberEntity;
import com.github.bestheroz.demo.entity.member.MemberRepository;
import com.github.bestheroz.demo.entity.member.config.MemberConfigEntity;
import com.github.bestheroz.demo.entity.member.config.MemberConfigRepository;
import com.github.bestheroz.demo.entity.menu.MenuRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import com.github.bestheroz.standard.common.util.AuthenticationUtils;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/members")
@Slf4j
public class MemberController {
  @Resource private MemberRepository memberRepository;
  @Resource private CodeRepository codeRepository;
  @Resource private MenuRepository menuRepository;
  @Resource private MemberConfigRepository memberConfigRepository;
  @Resource private AuthorityItemRepository authorityItemRepository;

  @GetMapping(value = "codes")
  ResponseEntity<ApiResult> getItems() {
    return Result.ok(this.codeRepository.getMembers());
  }

  @GetMapping(value = "mine")
  ResponseEntity<ApiResult> getMyInfo() {
    return Result.ok(
        this.memberRepository
            .findById(AuthenticationUtils.getId())
            .map(
                item -> {
                  item.setPassword(null);
                  return item;
                })
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER)));
  }

  @PatchMapping("mine")
  public ResponseEntity<ApiResult> editMe(@RequestBody final MemberEntity payload) {
    return this.memberRepository
        .findById(AuthenticationUtils.getId())
        .map(
            memberEntity -> {
              final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
              // 패스워드가 틀리면
              if (!pbkdf2PasswordEncoder.matches(
                  memberEntity.getPassword(),
                  pbkdf2PasswordEncoder.encode(payload.getPassword()))) {
                throw new BusinessException(ExceptionCode.FAIL_MATCH_PASSWORD);
              }
              memberEntity.setName(payload.getName());
              this.memberRepository.save(memberEntity);
              return Result.ok();
            })
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER));
  }

  @PatchMapping(value = "mine/password")
  public ResponseEntity<ApiResult> changePassword(@RequestBody final Map<String, String> payload) {
    return this.memberRepository
        .findById(AuthenticationUtils.getId())
        .map(
            memberEntity -> {
              final Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
              // 패스워드가 틀리면
              if (!pbkdf2PasswordEncoder.matches(
                  memberEntity.getPassword(),
                  pbkdf2PasswordEncoder.encode(payload.get("oldPassword")))) {
                throw new BusinessException(ExceptionCode.FAIL_MATCH_OLD_PASSWORD);
              }
              memberEntity.setPassword(payload.get("newPassword"));
              this.memberRepository.save(memberEntity);
              return Result.ok();
            })
        .orElseThrow(
            () -> {
              // 1. 유저가 없으면
              return new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER);
            });
  }

  @PostMapping(value = "mine/config")
  public ResponseEntity<ApiResult> changeConfig(@RequestBody final MemberConfigEntity payload) {
    return this.memberConfigRepository
        .findByMemberId(AuthenticationUtils.getId())
        .map(
            memberConfigEntity -> {
              BeanUtils.copyProperties(
                  payload,
                  memberConfigEntity,
                  "id",
                  "created",
                  "createdBy",
                  "updated",
                  "updatedBy",
                  "member");
              return Result.ok(this.memberConfigRepository.save(memberConfigEntity));
            })
        .orElseGet(
            () -> {
              payload.setMember(
                  this.memberRepository
                      .findById(AuthenticationUtils.getId())
                      .orElseThrow(
                          () -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER)));
              return Result.ok(this.memberConfigRepository.save(payload));
            });
  }

  @GetMapping(value = "mine/config")
  public ResponseEntity<ApiResult> getConfig() {
    return this.memberRepository
        .findById(AuthenticationUtils.getId())
        .map(memberEntity -> Result.ok(memberEntity.getConfig()))
        .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER));
  }

  @GetMapping(value = "mine/authority")
  ResponseEntity<ApiResult> getAuthority() {
    if (AuthenticationUtils.getLoginVO().getAuthority().equals("SUPER")) {
      return Result.ok(
          this.menuRepository.findAllByOrderByDisplayOrderAsc().stream()
              .map(
                  menu -> {
                    final AuthorityItemEntity authorityItemEntity = new AuthorityItemEntity();
                    authorityItemEntity.setAuthority("SUPER");
                    authorityItemEntity.setDisplayOrder(1);
                    authorityItemEntity.setTypesJson(List.of("VIEW", "WRITE", "DELETE"));
                    authorityItemEntity.setMenu(menu);
                    return authorityItemEntity;
                  }));
    } else {
      return Result.ok(
          this.authorityItemRepository.findAllByAuthorityOrderByDisplayOrderAsc(
              AuthenticationUtils.getLoginVO().getAuthority()));
    }
  }
}
