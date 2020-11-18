package com.twuc.shopping.exception;

public class ProductNameAlreadyExistException extends RuntimeException{
    public ProductNameAlreadyExistException(String message){
        super(message);
    }
}
