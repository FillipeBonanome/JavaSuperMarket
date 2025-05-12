package com.supermaket.marketapi.infra;

import com.supermaket.marketapi.dtos.ErrorDTO;
import com.supermaket.marketapi.exception.CartException;
import com.supermaket.marketapi.exception.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDTO> handleProductException(ProductException exception) {
        return ResponseEntity.badRequest().body(new ErrorDTO(ProductException.class.getSimpleName(), exception.getMessage()));
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ErrorDTO> handleCartException(CartException exception) {
        return ResponseEntity.badRequest().body(new ErrorDTO(CartException.class.getSimpleName(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> handleArgumentError(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(e -> new ErrorDTO(e.getField(), e.getDefaultMessage())).toList());
    }

}
