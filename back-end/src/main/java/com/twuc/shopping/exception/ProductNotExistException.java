package com.twuc.shopping.exception;

public class ProductNotExistException extends RuntimeException{
    public ProductNotExistException(String message){
        super(message);
    }
}
