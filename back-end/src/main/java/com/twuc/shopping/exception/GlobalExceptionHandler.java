package com.twuc.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNameAlreadyExistException.class)
    public ResponseEntity<ErrorResult> handle(ProductNameAlreadyExistException ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage(),400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorResult errorResult = new ErrorResult( "用户名不合法",400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

}
