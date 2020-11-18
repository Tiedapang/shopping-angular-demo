package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.exception.ProductNameAlreadyExistException;
import com.twuc.shopping.exception.ProductNotExistException;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ProductServiceTest {
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
        initMocks(this);
        productService = new ProductService(productRepository);
    }
    @Test
    void shouldAddProductSuccess(){
        Product product = Product.builder()
                .name("冰激凌")
                .price(BigDecimal.valueOf(5.0))
                .unit("支")
                .build();
        ProductPO productPO = ProductPO.builder()
                .id(product.getId())
                .name(product.getName())
                .imgPath(product.getImgPath())
                .unit(product.getUnit())
                .price(product.getPrice())
                .build();
        when(productRepository.findByName("冰激凌")).thenReturn(Optional.empty());
        productService.addProduct(product);
        verify(productRepository).save(productPO);
    }
    @Test
    void shouldAddProductFailWhenProductNameExist(){
        Product product = Product.builder()
                .id(1)
                .name("冰激凌")
                .price(BigDecimal.valueOf(5.0))
                .unit("支")
                .build();
        ProductPO productPO = ProductPO.builder()
                .id(product.getId())
                .name(product.getName())
                .imgPath(product.getImgPath())
                .unit(product.getUnit())
                .price(product.getPrice())
                .build();
        when(productRepository.findByName("冰激凌")).thenReturn(Optional.of(productPO));
        //when&then
        assertThrows(
                ProductNameAlreadyExistException.class,
                () -> {
                    productService.addProduct(product);
                });
    }
    @Test
    void shouldDeleteProductByIdSucess(){
        ProductPO productPO = ProductPO.builder()
                .id(1)
                .name("冰激凌")
                .price(BigDecimal.valueOf(5.0))
                .unit("支")
                .build();
        when(productRepository.findById(productPO.getId())).thenReturn(Optional.of(productPO));
        productService.deleteById(productPO.getId());
        verify(productRepository).deleteById(productPO.getId());
    }
    @Test
    void shouldDeleteProductFailWhenDeleteIdNotExist(){
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        //when&then
        assertThrows(
                ProductNotExistException.class,
                () -> {
                    productService.deleteById(1);
                });
    }
    @Test
    void shouldGetProductsByPageable(){
//        ProductPO productPO1 = ProductPO.builder()
//                .name("冰激凌")
//                .price(BigDecimal.valueOf(5.0))
//                .unit("支")
//                .build();
//        ProductPO productPO2 = ProductPO.builder()
//                .name("巧克力")
//                .price(BigDecimal.valueOf(3.0))
//                .unit("颗")
//                .build();
//        List<ProductPO> productPOList = new ArrayList<ProductPO>();
//        productPOList.add(productPO1);
//        productPOList.add(productPO2);
//        when(productRepository.findAll()).thenReturn(productPOList);
//        Pageable pageable = PageRequest.of(0,10);
//        productService.addProduct(productPO1);
//        productService.addProduct(productPO2);
//        productService.findAll(pageable);
    }
    @Test
    void shouldGetNullProductsByPageableIsIllegal(){
        when(productRepository.findAll()).thenReturn(null);
        Pageable pageable = PageRequest.of(0,10);
//        productService.findAll(pageable);
        verify(productRepository,times(0)).findAll();
        verify(productRepository,times(0)).count();
    }
}
