package com.github.bestheroz.demo.entity.member.config;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberConfigRepository
    extends CrudRepository<MemberConfigEntity, Long>, QueryByExampleExecutor<MemberConfigEntity> {
  Optional<MemberConfigEntity> findByMemberId(Long memberId);
}
