package com.backend.exceptionhandlers.validation;

import com.backend.dtos.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationHandler(MethodArgumentNotValidException e) {
        var response = ErrorResponse.builder()
                .message("validation error!")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            response.addValidationError(field, message);
        });

        return ResponseEntity.badRequest().body(response);
    }
}
