package com.example.HospitalManagement.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateUsername(RuntimeException ex){
        return new ResponseEntity<>( new ExceptionResponse().builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                        .build(),HttpStatus.CONFLICT);

    }
}
