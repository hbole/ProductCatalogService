package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.exceptions.CustomException;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<String> handleException(CustomException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }
}
