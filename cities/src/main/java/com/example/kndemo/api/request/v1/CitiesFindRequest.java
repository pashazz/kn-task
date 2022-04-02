package com.example.kndemo.api.request.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Builder
@Value
@Jacksonized
public class CitiesFindRequest {
    @NotBlank(message = "query should not be blank")
    String query;
}
