package com.example.kndemo.gateway.clients;

import com.example.kndemo.gateway.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;


public interface AuthClient {
    Mono<UserDto> validateToken(@PathVariable("token") String token);
}
