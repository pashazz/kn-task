package com.example.kndemo.api.request.v1;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Jacksonized
@Builder
@Value
public class CityPatchRequest {
    @NotBlank(message = "city name can't be empty")
    Optional<String> name;
    @NotBlank(message = "photo can't be empty")
    Optional<String> photo;
}
