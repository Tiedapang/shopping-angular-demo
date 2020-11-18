package com.twuc.shopping.responsePo;

import com.twuc.shopping.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductResponse {
    private List<Product> productList;
    private long totalCount;
}
