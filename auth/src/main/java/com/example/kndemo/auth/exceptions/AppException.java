package com.example.kndemo.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AppException extends ResponseStatusException {

    public AppException(String message, HttpStatus status) {
        super(status, message);
    }
}
