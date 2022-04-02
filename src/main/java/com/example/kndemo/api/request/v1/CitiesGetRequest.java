package com.example.kndemo.api.request.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;

@Jacksonized
@Builder
@Value
public class CitiesGetRequest {
    @Min(value = 0, message = "the page number can't be a negative number. Use page 0..size")
    private Integer page;
    @Min(value = 1, message = "the size of page can't be a negative number or zero")
    private Integer size;
}
