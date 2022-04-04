package com.example.kndemo.controllers.common;

import com.example.kndemo.api.wrapper.v1.ApiWrapperV1;
import com.example.kndemo.exceptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiWrapperV1<Void>> defaultExcHandler(Throwable t){
        t.printStackTrace();
        return ResponseEntity.internalServerError()
                .body(ApiWrapperV1.ofError(
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        String.format("%s: %s",
                                t.getClass().getSimpleName(),
                                t.getMessage())
                        ));
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ApiWrapperV1<Void>> notFoundHandler(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiWrapperV1.ofError(
                        HttpStatus.NOT_FOUND.name(),
                        "Not found"));
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ApiWrapperV1<Void>> responseStatusHandler(ResponseStatusException e) {
        return  ResponseEntity.status(e.getStatus())
                .body(ApiWrapperV1.ofError(e.getStatus().name(), e.getMessage()));
    }

    @ExceptionHandler({CityNotFoundException.class})
    public ResponseEntity<ApiWrapperV1<Void>> cityNotFoundHandler(CityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiWrapperV1.ofError(
                        HttpStatus.NOT_FOUND.name(),
                        ex.getMessage()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ApiWrapperV1<Void>> badRequestHandler(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiWrapperV1.ofError(
                        HttpStatus.BAD_REQUEST.name(),
                        ex.getMessage()));
    }
}
