package com.ess.registration.core.exception;

import org.springframework.http.HttpStatus;

public class DuplicateException extends RuntimeException{

    private final HttpStatus status;

    public DuplicateException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
