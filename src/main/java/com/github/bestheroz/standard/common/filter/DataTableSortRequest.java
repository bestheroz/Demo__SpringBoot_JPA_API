package com.github.bestheroz.standard.common.filter;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataTableSortRequest {
  private int page;
  private List<String> sortBy;
  private List<Boolean> sortDesc;
  private Integer itemsPerPage;

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
}
