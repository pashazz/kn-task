package com.example.kndemo.controllers.common;

import com.example.kndemo.api.wrapper.v1.ApiWrapper;
import com.example.kndemo.exceptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

   /** @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "fail");
        response.put("message", e.getLocalizedMessage());
        return response;
    }

    **/
}
