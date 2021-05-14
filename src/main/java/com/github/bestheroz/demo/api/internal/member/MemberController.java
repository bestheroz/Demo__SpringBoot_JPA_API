package com.github.bestheroz.demo.api.internal.member;

import com.github.bestheroz.demo.entity.authority.AuthorityRepository;
import com.github.bestheroz.demo.entity.authority.item.AuthorityItemEntity;
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
import com.github.bestheroz.standard.common.util.MapperUtils;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
  @Resource private AuthorityRepository authorityRepository;
  @Resource private CodeRepository codeRepository;
  @Resource private MenuRepository menuRepository;
  @Resource private MemberConfigRepository memberConfigRepository;

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

  @PostMapping(value = "mine/changePassword")
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
    return Result.ok(
        this.authorityRepository
            .findById(AuthenticationUtils.getLoginVO().getAuthorityId())
            .map(
                authority -> {
                  if (authority.getCode().equals("SUPER")) {
                    authority.setItems(
                        this.menuRepository.findAllByOrderByDisplayOrderAsc().stream()
                            .map(
                                menu -> {
                                  final AuthorityItemEntity authorityItemEntity =
                                      MapperUtils.toObject(menu, AuthorityItemEntity.class);
                                  authorityItemEntity.setMenu(menu);
                                  authorityItemEntity.setTypesJson(List.of("VIEW"));
                                  return authorityItemEntity;
                                })
                            .collect(Collectors.toList()));
                  }
                  return authority;
                })
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NOT_ALLOWED_MEMBER)));
  }
}
