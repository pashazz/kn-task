package com.example.kndemo.exceptions;

import java.util.UUID;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(UUID id) {
        super(String.format("Could not find a City with id %s", id));
    }
}
