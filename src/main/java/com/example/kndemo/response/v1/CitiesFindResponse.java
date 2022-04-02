package com.example.kndemo.response.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
@NonNull
public class CitiesFindResponse {
    List<CityResponse> cities;
}
