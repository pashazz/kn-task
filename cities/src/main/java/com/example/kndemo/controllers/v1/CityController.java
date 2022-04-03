package com.example.kndemo.controllers.v1;

import com.example.kndemo.api.method.v1.impl.CitiesFindMethod;
import com.example.kndemo.api.method.v1.impl.CitiesGetMethod;
import com.example.kndemo.api.method.v1.impl.CityPatchMethod;
import com.example.kndemo.api.request.v1.CitiesFindRequest;
import com.example.kndemo.api.request.v1.CityPatchRequest;
import com.example.kndemo.api.response.v1.CitiesFindResponse;
import com.example.kndemo.api.response.v1.CitiesGetResponse;
import com.example.kndemo.api.wrapper.v1.ApiWrapperV1;
import com.example.kndemo.api.request.v1.CitiesGetRequest;
import com.example.kndemo.controllers.HeaderHelper;
import com.example.kndemo.services.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("v1/")
@RequiredArgsConstructor
@Slf4j
public class CityController {

    private final CitiesFindMethod citiesFindMethod;
    private final CityPatchMethod cityPatchMethod;
    private final CitiesGetMethod cityGetMethod;
    private final HeaderHelper headerHelper = HeaderHelper.INSTANCE;
    private final TokenService tokenService;

    @GetMapping(value = "/city/get", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapperV1<CitiesGetResponse>> getCities(@Valid @RequestBody Optional<CitiesGetRequest> request) {

        return ok(cityGetMethod.execute(request.orElse(defaultGetCitiesRequest())));
    }

    @GetMapping(value = "/city/find", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapperV1<CitiesFindResponse>> findCities(
            @Valid @RequestBody CitiesFindRequest request
    ) {
        return ok(citiesFindMethod.execute(request));
    }

    @PatchMapping(value = "/city", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapperV1<Void>> patchCity(@Valid @RequestBody CityPatchRequest request,
                                                        @RequestHeader HttpHeaders headers) {
        boolean canEdit = headerHelper.getToken(headers)
                .map(tokenService::canEditCities)
                .orElse(false);
        if (canEdit) {
            return ok(cityPatchMethod.execute(request));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    private <T> ResponseEntity<ApiWrapperV1<T>> ok(T response) {
        return ResponseEntity.ok(ApiWrapperV1.of(response));
    }

    private CitiesGetRequest defaultGetCitiesRequest() {
        return CitiesGetRequest.builder()
                .page(0)
                .size(10)
                .build();
    }
}
