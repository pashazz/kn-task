package com.example.kndemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Value
@Builder
@AllArgsConstructor
@NonNull
public class CityDTO {
    UUID id;

    String name;

    String url;
}
