package com.twuc.shopping.repository;

import com.twuc.shopping.po.ProductPO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductPO, Integer> {
  List<ProductPO> findAll();
  Optional<ProductPO> findByName(String name);
  Page<ProductPO> findAll(Pageable pageable);
  @Query(value =
          "SELECT * from product where name like %:productName% and price >= :productMinPrice and price <= :productMaxPrice/* #pageable# */",
          countQuery="select count(*) from where name like %:productName% and price >= :productMinPrice and price <= :productMaxPrice",
          nativeQuery = true)
  Page<ProductPO> findAll(@Param("productName") String productName, @Param("productMinPrice") BigDecimal productMinPrice, @Param("productMaxPrice") BigDecimal productMaxPrice, Pageable pageable);

}
