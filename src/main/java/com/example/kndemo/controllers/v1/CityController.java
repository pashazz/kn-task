package com.example.kndemo.controllers.v1;

import com.example.kndemo.api.request.v1.CitiesFindRequest;
import com.example.kndemo.api.request.v1.CityPatchRequest;
import com.example.kndemo.api.wrapper.v1.ApiWrapper;
import com.example.kndemo.api.request.v1.CitiesGetRequest;
import com.example.kndemo.response.v1.CitiesGetResponse;
import com.example.kndemo.response.v1.CitiesFindResponse;
import com.example.kndemo.services.CityService;
import liquibase.repackaged.org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping("v1/")
public class CityController {
    @GetMapping(value = "/city/get", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapper<CitiesGetResponse>> getCities(@Valid @RequestBody CitiesGetRequest request) {
        throw new  NotImplementedException();
    }

    @GetMapping(value = "/city/find", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapper<CitiesFindResponse>> findCities(@Valid @RequestBody CitiesFindRequest request) {
        throw new  NotImplementedException();
    }

    @PatchMapping(value = "/city/${cityId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiWrapper<Void>> patchCity(@Valid @RequestBody CityPatchRequest request,
                                                      @NotBlank UUID cityUUID) {
        throw new  NotImplementedException();
    }
}
