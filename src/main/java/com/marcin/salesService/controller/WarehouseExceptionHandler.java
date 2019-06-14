package com.marcin.salesService.controller;

import com.marcin.salesService.exception.NotEnoughtProductInWarehouseException;
import com.marcin.salesService.exception.ProductErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WarehouseExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(NotEnoughtProductInWarehouseException e){

        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e){

        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
