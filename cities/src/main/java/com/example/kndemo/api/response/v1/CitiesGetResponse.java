package com.example.kndemo.api.response.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
@NonNull
public class CitiesGetResponse {
    @NonNull
    List<CityResponse> cities;
    int page;
    int totalPages;
}
