package com.example.kndemo.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Value
@Builder
public class CityDTO {
    UUID id;
    String name;
    String photo;
}
