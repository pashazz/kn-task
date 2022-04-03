package com.example.kndemo.gateway.requestsettings;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDRequestIDGenerator implements RequestIDGenerator{

    @Override
    public String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}
