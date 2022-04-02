package com.example.kndemo.api.method.v1.impl;

import com.example.kndemo.api.method.v1.MethodV1;
import com.example.kndemo.api.request.v1.CitiesFindRequest;
import com.example.kndemo.api.response.v1.CitiesFindResponse;
import com.example.kndemo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CitiesFindMethod implements MethodV1<CitiesFindRequest, CitiesFindResponse> {

    private final CityService cityService;

    @Autowired
    public CitiesFindMethod(CityService cityService) {
        this.cityService = cityService;
    }





    @Override
    public CitiesFindResponse execute(CitiesFindRequest request) {
        var dto = cityService.findCities(request.getQuery());
        return getResponseMapper().dtoToFindResponse(dto);
    }
}
