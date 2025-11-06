package com.MyProject.InsuranceApp.exception;

import com.MyProject.InsuranceApp.response.ApiHeader;
import com.MyProject.InsuranceApp.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ApiHeader header = new ApiHeader("FAILURE", "Validation failed", "CREATE_APPLICATION");
        ApiResponse<Object> response = new ApiResponse<>(header, null, errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AppAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppAlreadyExists(AppAlreadyExistsException ex) {
        ApiHeader header = new ApiHeader("FAILURE", ex.getMessage(), "CREATE_APPLICATION");
        ApiResponse<Object> response = new ApiResponse<>(header, null, null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT); // 409 Conflict
    }
}

