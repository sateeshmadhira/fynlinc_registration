package com.ess.registration.core.exception;

public class InvalidOrganizationTypeException extends RuntimeException {

    // Constructor accepting a custom message
    public InvalidOrganizationTypeException(String message) {
        super(message);
    }

    // Constructor accepting a custom message and cause
    public InvalidOrganizationTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}

