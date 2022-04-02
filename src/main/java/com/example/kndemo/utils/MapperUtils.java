package com.example.kndemo.utils;

import java.util.Optional;

public interface MapperUtils {
    default <T> T mapFromOptional(Optional<T> value) {
        return value.orElse(null);
    }
}
