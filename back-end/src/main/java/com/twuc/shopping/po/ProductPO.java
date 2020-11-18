package com.twuc.shopping.po;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductPO {
  @Id
  @GeneratedValue
  private int id;
  private String name;
  private BigDecimal price;
  private String unit;
  private String imgPath;
}
