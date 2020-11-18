package com.twuc.shopping.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.twuc.shopping.responsePo.ProductResponse;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import com.twuc.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.twuc.shopping.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;

@RestController
@Validated
@RequestMapping("/product")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductPO> getProductListByPageAble(@RequestParam(value = "pageSize") int pageSize,
                                                    @RequestParam(value = "pageNumber") int pageNumber,
                                                    @RequestParam(value = "productName",required = false) String productName,
                                                    @RequestParam(value = "productMinPrice",required = false) BigDecimal productMinPrice,
                                                    @RequestParam(value = "productMaxPrice",required = false) BigDecimal productMaxPrice) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ProductPO> productPOS = productService.findAll(pageable, productName, productMinPrice, productMaxPrice);
        return productPOS;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) throws JsonProcessingException {
        productService.deleteById(Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid Product product) throws JsonProcessingException {
        productService.addProduct(product);
        return ResponseEntity.created(null).build();
    }
}
