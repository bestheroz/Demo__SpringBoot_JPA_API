package com.github.bestheroz.demo.api.internal.app;

import com.github.bestheroz.demo.domain.App;
import com.github.bestheroz.demo.repository.AppRepository;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.exception.ExceptionCode;
import com.github.bestheroz.standard.common.filter.DataTableSortRequest;
import com.github.bestheroz.standard.common.response.ApiResult;
import com.github.bestheroz.standard.common.response.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "api/apps/")
@RequiredArgsConstructor
public class AppController {
  private final AppRepository appRepository;

  @GetMapping
  ResponseEntity<ApiResult<Page<App>>> getApps(
      @RequestParam(required = false) final String search,
      final DataTableSortRequest dataTableSortRequest,
      final AppFilter appFilter) {

    return Result.ok(
        this.appRepository.findAllBySearch(
            search,
            appFilter.getAvailableList(),
            appFilter.getAppPlatformTypeList(),
            dataTableSortRequest.getPageRequest()));
  }

  @PostMapping
  public ResponseEntity<ApiResult<App>> post(@RequestBody final App payload) {
    return Result.created(this.appRepository.save(payload));
  }

  @PatchMapping(value = "{id}")
  public ResponseEntity<ApiResult<App>> patch(
      @PathVariable(value = "id") final Long id, @RequestBody final App payload) {
    return Result.ok(
        this.appRepository
            .findById(id)
            .map(
                (item) -> {
                  item.changeApp(
                      payload.getName(),
                      payload.getPlatform(),
                      payload.getDescription(),
                      payload.getAvailable());
                  return this.appRepository.save(item);
                })
            .orElseThrow(() -> new BusinessException(ExceptionCode.FAIL_NO_DATA_SUCCESS)));
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<ApiResult<?>> delete(@PathVariable(value = "id") final Long id) {
    this.appRepository.deleteById(id);

    return Result.ok();
  }
}
