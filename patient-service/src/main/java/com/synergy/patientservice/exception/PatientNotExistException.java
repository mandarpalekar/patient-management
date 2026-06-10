package com.synergy.patientservice.exception;

public class PatientNotExistException extends RuntimeException {
    public PatientNotExistException(String message) {
        super(message);
    }
}
