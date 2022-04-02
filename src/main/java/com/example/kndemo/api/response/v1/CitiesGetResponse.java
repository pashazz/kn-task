package com.example.kndemo.api.response.v1;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CitiesGetResponse {
    List<CityResponse> cities;
    int page;
    int totalPages;
}
