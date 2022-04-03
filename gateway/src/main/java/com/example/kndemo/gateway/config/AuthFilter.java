package com.example.kndemo.gateway.config;


import com.example.kndemo.gateway.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final WebClient.Builder webClientBuilder;

    @Value("${kndemo.auth-header:X-Token}")
    private String authHeaderName;

    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Missing authorization information");
            }


            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            String[] parts = authHeader.split(" ");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect authorization structure");
            }

            return webClientBuilder.build()
                    .post()
                    .uri("http://auth/auth/validateToken?token=" + parts[1])
                    .retrieve().bodyToMono(UserDto.class)
                    .map(userDto -> {
                        exchange.getRequest()
                                .mutate()
                                .header(authHeaderName, String.valueOf(userDto.getId()));
                        return exchange;
                    }).flatMap(chain::filter);
        };
    }

    public static class Config {
        // empty class as I don't need any particular configuration
    }
}
