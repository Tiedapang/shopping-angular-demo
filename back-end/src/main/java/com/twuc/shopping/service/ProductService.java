package com.twuc.shopping.service;


import com.twuc.shopping.domain.Product;
import com.twuc.shopping.exception.ErrorMessage;
import com.twuc.shopping.exception.ProductNameAlreadyExistException;
import com.twuc.shopping.exception.ProductNotExistException;
import com.twuc.shopping.responsePo.ProductResponse;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository1) {
        this.productRepository = productRepository1;

    }

    public void deleteById(int deleteID) {
        if (productRepository.findById(deleteID).isPresent()) {
            productRepository.deleteById(deleteID);
        } else {
            throw new ProductNotExistException(ErrorMessage.PRODUCT_NOT_EXIST);
        }

    }

    public void addProduct(Product product) {
        ProductPO productPO = ProductPO.builder()
                .id(product.getId())
                .name(product.getName())
                .imgPath(product.getImgPath())
                .unit(product.getUnit())
                .price(product.getPrice())
                .build();
        if (productPO.getId() == 0) {
            Optional<ProductPO> productPOOptional = productRepository.findByName(productPO.getName());
            if (productPOOptional.isPresent()) {
                throw new ProductNameAlreadyExistException(ErrorMessage.PRODUCT_NAME_ALLREADY_EXIST);
            }
        }
        productRepository.save(productPO);
    }

    public ProductPO getProductById(int product_id) {
        return productRepository.findById(product_id).get();
    }

    public Page<ProductPO> findAll(Pageable pageable, String productName, BigDecimal productMinPrice, BigDecimal productMaxPrice) {
        ProductResponse productResponse = new ProductResponse();
        Page<ProductPO> productPOS = null;
        if (productName== null &&productMaxPrice==null&&productMaxPrice==null) {
            productPOS = productRepository.findAll(pageable);
        } else{
            productPOS = productRepository.findAll(productName, productMinPrice, productMaxPrice, pageable);
        }

        return productPOS;
    }
}
