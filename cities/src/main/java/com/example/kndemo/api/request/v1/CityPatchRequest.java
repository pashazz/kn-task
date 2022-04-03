package com.example.kndemo.api.request.v1;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Jacksonized
@Builder
@Value
public class CityPatchRequest {
    String name;
    String url;

    @NotNull
    @NonNull
    UUID id;
}
