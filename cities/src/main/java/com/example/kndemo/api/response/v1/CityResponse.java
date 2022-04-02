package com.example.kndemo.api.response.v1;


import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class CityResponse {
    UUID id;

    String name;

    String url;
}
