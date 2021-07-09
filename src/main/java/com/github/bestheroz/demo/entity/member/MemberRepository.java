package com.github.bestheroz.demo.entity.member;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository
    extends CrudRepository<MemberEntity, Long>, QueryByExampleExecutor<MemberEntity> {
  Optional<MemberEntity> findByUserIdAndToken(String userId, String token);

  Optional<MemberEntity> findByUserId(String userId);

  List<MemberEntity> findAll(Sort sort);

  @Query(
      "SELECT m FROM member m WHERE (coalesce(:available, null) is null or m.available IN (:available)) "
          + "and (coalesce(:authority, null) is null or m.authority IN (:authority)) "
          + "and (:search is null or :search = '' or :search = '%%' or m.name like %:search% or m.userId like %:search%)")
  Page<MemberEntity> findAllByDTO(
      @Param("search") String search,
      @Param("available") List<Boolean> availableList,
      @Param("authority") List<String> authorityList,
      Pageable pageable);
}
