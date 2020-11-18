package com.twuc.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product implements Serializable {
  @NotNull private int id;
  @NotNull private String name;
  @Min(0)
  @NotNull private BigDecimal price;
  @NotNull private String unit;
  private String imgPath;
}
