package com.example.kndemo.controllers.common;

import com.example.kndemo.api.wrapper.v1.ApiWrapper;
import com.example.kndemo.exceptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiWrapper<Void>> defaultExcHandler(Throwable t){
        return ResponseEntity.internalServerError()
                .body(ApiWrapper.ofError(
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        String.format("%s: %s",
                                t.getClass().getSimpleName(),
                                t.getMessage())
                        ));
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ApiWrapper<Void>> notFoundHandler(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiWrapper.ofError(
                        HttpStatus.NOT_FOUND.name(),
                        "Not found"));
    }

    @ExceptionHandler({CityNotFoundException.class})
    public ResponseEntity<ApiWrapper<Void>> cityNotFoundHandler(CityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiWrapper.ofError(
                        HttpStatus.NOT_FOUND.name(),
                        ex.getMessage()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ApiWrapper<Void>> badRequestHandler(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiWrapper.ofError(
                        HttpStatus.BAD_REQUEST.name(),
                        ex.getMessage()));
    }
}
