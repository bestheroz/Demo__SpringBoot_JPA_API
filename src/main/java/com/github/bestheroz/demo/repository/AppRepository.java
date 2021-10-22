package com.github.bestheroz.demo.repository;

import com.github.bestheroz.demo.domain.App;
import com.github.bestheroz.demo.repository.custom.AppRepositoryCustom;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository
    extends CrudRepository<App, Long>, QueryByExampleExecutor<App>, AppRepositoryCustom {

  List<App> findAll(Sort sort);
}
