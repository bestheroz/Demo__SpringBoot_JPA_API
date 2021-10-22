package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.AdminConfig;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminConfigRepository
    extends CrudRepository<AdminConfig, Long>, QueryByExampleExecutor<AdminConfig> {
  Optional<AdminConfig> findByAdminId(Long adminId);
}
