package com.example.kndemo.api.response.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Builder
@Value
@NonNull
public class CitiesFindResponse {
    List<CityResponse> cities;
}
