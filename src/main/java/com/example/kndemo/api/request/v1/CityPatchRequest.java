package com.example.kndemo.api.request.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Builder
@Value
public class CityPatchRequest {
    @NotBlank(message = "city name can't be empty")
    String name;
    @NotBlank(message = "photo can't be empty")
    String photo;
    @NotBlank(message = "ID can't be blank")
    @NonNull
    UUID id;
}
