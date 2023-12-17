package com.backend.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String message;
    private int status;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class ValidationError {
        private String field;
        private String message;
    }

    private List<ValidationError> errors = null;

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) errors = new ArrayList<>();
        errors.add(new ValidationError(field, message));
    }
}
