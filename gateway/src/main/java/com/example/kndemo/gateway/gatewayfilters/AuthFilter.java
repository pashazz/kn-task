package com.example.kndemo.gateway.gatewayfilters;


import com.example.kndemo.constants.CommonConstants;
import com.example.kndemo.gateway.clients.AuthClient;
import com.example.kndemo.gateway.dto.UserDto;
import com.example.kndemo.gateway.requestsettings.RequestIDGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {


    private final AuthClient authClient;
    private final RequestIDGenerator generator;

    public AuthFilter(AuthClient authClient, RequestIDGenerator generator) {
        super(Config.class);
        this.authClient = authClient;
        this.generator = generator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing authorization information");
            }

            String authHeader =
                    Optional.of(exchange.getRequest())
                            .map(HttpMessage::getHeaders)
                            .map(h -> h.get(HttpHeaders.AUTHORIZATION))
                            .map(l -> l.get(0))
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No authorization header"));

            String[] parts = authHeader.split("\\s+");

            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect authorization structure");
            }

            String token = parts[1];
            return authClient.validateToken(token)
                    .map(userDto -> {
                        exchange.getRequest()
                                .mutate()
                                .header(CommonConstants.AUTH_HEADER_NAME, token)
                                .header(CommonConstants.REQUEST_ID_HEADER_NAME,  generator.generateRequestId());
                        return exchange;
                    }).flatMap(chain::filter);
        };
    }

    public static class Config {
        // empty class as I don't need any particular configuration
    }
}
