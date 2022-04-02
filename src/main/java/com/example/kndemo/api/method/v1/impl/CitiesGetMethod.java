package com.example.kndemo.api.method.v1.impl;

import com.example.kndemo.api.method.v1.MethodV1;
import com.example.kndemo.api.request.v1.CitiesGetRequest;
import com.example.kndemo.api.response.v1.CitiesGetResponse;
import com.example.kndemo.services.CityService;
import org.springframework.stereotype.Component;

@Component
public class CitiesGetMethod implements MethodV1<CitiesGetRequest, CitiesGetResponse> {

    private final CityService cityService;

    public CitiesGetMethod(CityService cityService) {
        this.cityService = cityService;
    }


    @Override
    public CitiesGetResponse execute(CitiesGetRequest request) {
        var dto = cityService.getCities(request.getPage(), request.getSize());
        return getResponseMapper().dtoToGetResponse(dto);
    }
}
