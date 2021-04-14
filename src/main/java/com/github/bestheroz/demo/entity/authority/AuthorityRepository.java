package com.github.bestheroz.demo.entity.authority;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {
  List<AuthorityEntity> findAllById(Long id);

  List<AuthorityEntity> findAllByOrderByNameAsc();

  void deleteAllById(Long id);

  @Query(
      value = "select a.id as value, a.name as text from authority a order by a.name asc",
      nativeQuery = true)
  List<Object[]> getCodes();
}
