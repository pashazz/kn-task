package com.example.kndemo.gateway.clients;

import com.example.kndemo.gateway.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthClientImpl implements AuthClient {
    @Value("${kndemo.services.auth:http://auth}")
    private String serviceUrl;

    private final WebClient.Builder webClientBuilder;


    protected String getAuthValidationUrl(String token) {
        return String.format("%s/auth/validateToken?token=%s", serviceUrl, token);
    }

    @Override
    public Mono<UserDto> validateToken(String token) {
        return webClientBuilder.build()
                .post()
                .uri(getAuthValidationUrl(token))
                .retrieve().bodyToMono(UserDto.class);
    }
}
