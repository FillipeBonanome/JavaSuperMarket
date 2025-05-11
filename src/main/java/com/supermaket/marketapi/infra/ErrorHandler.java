package com.supermaket.marketapi.infra;

import com.supermaket.marketapi.dtos.ErrorDTO;
import com.supermaket.marketapi.exception.ProductException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDTO> HandleProductException(ProductException exception) {
        return ResponseEntity.badRequest().body(new ErrorDTO(ProductException.class.getSimpleName(), exception.getMessage()));
    }

}
