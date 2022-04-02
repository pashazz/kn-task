package com.example.kndemo.controllers.v1;

import com.example.kndemo.api.method.v1.impl.CitiesFindMethod;
import com.example.kndemo.api.method.v1.impl.CitiesGetMethod;
import com.example.kndemo.api.method.v1.impl.CityPatchMethod;
import com.example.kndemo.api.request.v1.CitiesFindRequest;
import com.example.kndemo.api.request.v1.CityPatchRequest;
import com.example.kndemo.api.response.v1.CitiesFindResponse;
import com.example.kndemo.api.response.v1.CitiesGetResponse;
import com.example.kndemo.api.wrapper.v1.ApiWrapper;
import com.example.kndemo.api.request.v1.CitiesGetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("v1/")
public class CityController {

    private final CitiesFindMethod citiesFindMethod;
    private final CityPatchMethod cityPatchMethod;
    private final CitiesGetMethod cityGetMethod;


    public CityController(CitiesFindMethod citiesFindMethod,
                          CityPatchMethod cityPatchMethod,
                          CitiesGetMethod cityGetMethod) {


        this.citiesFindMethod = citiesFindMethod;
        this.cityPatchMethod = cityPatchMethod;
        this.cityGetMethod = cityGetMethod;
    }

    @GetMapping(value = "/city/get", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapper<CitiesGetResponse>> getCities(@Valid @RequestBody Optional<CitiesGetRequest> request) {

        return ok(cityGetMethod.execute(request.orElse(defaultGetCitiesRequest())));
    }

    @GetMapping(value = "/city/find", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapper<CitiesFindResponse>> findCities(@Valid @RequestBody CitiesFindRequest request) {
        return ok(citiesFindMethod.execute(request));
    }

    @PatchMapping(value = "/city", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapper<Void>> patchCity(@Valid @RequestBody CityPatchRequest request) {
        return ok(cityPatchMethod.execute(request));
    }

    private <T> ResponseEntity<ApiWrapper<T>> ok(T response) {
        return ResponseEntity.ok(ApiWrapper.of(response));
    }

    private CitiesGetRequest defaultGetCitiesRequest() {
        return CitiesGetRequest.builder()
                .page(0)
                .size(10)
                .build();
    }
}
