package com.fishing.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RegistrationExceptionHandler {

    @ExceptionHandler(value = RegistrationException.class)
    public ResponseEntity<ErrorResponse> handleMemberRegistrationException(RegistrationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(400, "Bad Request", "REGISTRATION_ERROR", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
