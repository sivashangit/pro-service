package com.siva.springproduct.handler;

import com.siva.springproduct.exception.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFound productNotFound){
    return new ResponseEntity<>(productNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }
}
