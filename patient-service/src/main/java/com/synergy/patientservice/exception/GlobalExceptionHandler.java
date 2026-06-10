package com.synergy.patientservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        log.warn("Email message already exists {}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("email", "Email already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotExistException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotExistException(PatientNotExistException ex) {
        log.warn("Patient does not exists {}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("patient", "Patient does not exists");
        return ResponseEntity.badRequest().body(errors);
    }
}
