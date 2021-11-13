package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.Admin;
import com.github.bestheroz.demo.repository.custom.AdminRepositoryCustom;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository
    extends CrudRepository<Admin, Long>, QueryByExampleExecutor<Admin>, AdminRepositoryCustom {
  Optional<Admin> findByIdAndToken(Long id, String token);

  Optional<Admin> findByAdminId(String adminId);

  boolean existsByAdminId(String adminId);
}
