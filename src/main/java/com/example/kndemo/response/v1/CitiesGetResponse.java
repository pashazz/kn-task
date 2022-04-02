package com.example.kndemo.response.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@SuperBuilder
@Jacksonized
@NonNull
public class CitiesGetResponse {
    List<CityResponse> cities;
    int pageSize;
    int pageNumber;
    int total;
}
