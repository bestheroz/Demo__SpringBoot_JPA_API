package com.github.bestheroz.standard.common.filter;

import com.github.bestheroz.standard.common.exception.BusinessException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@Data
@Slf4j
public class DataTableFilterDTO {
  private Map<String, Object> filter;
  private int page;
  private List<String> sortBy;
  private List<Boolean> sortDesc;
  private short itemsPerPage;

  private Sort getSort() {
    final List<Order> orders = new ArrayList<>();
    for (int i = 0; i < this.sortBy.size(); i++) {
      orders.add(
          this.sortDesc.get(i) ? Order.desc(this.sortBy.get(i)) : Order.asc(this.sortBy.get(i)));
    }
    return Sort.by(orders);
  }

  public PageRequest getPageRequest() {
    return PageRequest.of(this.getPage() - 1, this.getItemsPerPage(), this.getSort());
  }

  public <T> Example<T> getExample(final T filter) {
    ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
    if (this.getFilter() != null) {
      for (final Entry<String, Object> entry : this.getFilter().entrySet()) {
        final String key = entry.getKey();
        final String value = (String) entry.getValue();
        final String columnName = StringUtils.substringBefore(key, ":");
        final String methodString =
            "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
        for (final Method method : filter.getClass().getDeclaredMethods()) {
          if (methodString.equals(method.getName())) {
            try {
              if (method.getParameterTypes()[0].getSimpleName().equals("Integer")) {
                method.invoke(filter, Integer.valueOf(value));
              } else if (method.getParameterTypes()[0].getSimpleName().equals("Boolean")) {
                method.invoke(filter, Boolean.valueOf(value));
              } else {
                method.invoke(filter, value);
              }
              if (StringUtils.equalsAny(
                  StringUtils.substringAfter(key, ":"), "equals", "set", "booleanEquals")) {
                matcher = matcher.withMatcher(columnName, GenericPropertyMatchers.exact());
              } else {
                matcher =
                    matcher.withMatcher(
                        columnName, GenericPropertyMatchers.contains().ignoreCase());
              }
            } catch (final Exception e) {
              log.warn(ExceptionUtils.getStackTrace(e));
              throw new BusinessException(e);
            }
            break;
          }
        }
      }
    }
    return Example.of(filter, matcher);
  }
}
