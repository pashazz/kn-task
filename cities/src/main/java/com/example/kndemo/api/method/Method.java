package com.example.kndemo.api.method;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Supplier;

public interface Method<IN, OUT> {
     OUT execute(IN request);



}
