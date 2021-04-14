package com.github.bestheroz.demo.entity.member;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository
    extends CrudRepository<MemberEntity, Long>, QueryByExampleExecutor<MemberEntity> {
  Optional<MemberEntity> findByUserIdAndToken(String userId, String token);

  Optional<MemberEntity> findByUserId(String userId);

  List<MemberEntity> findAll(Sort sort);
}
