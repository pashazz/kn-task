package com.example.kndemo.api.method.v1.impl;

import com.example.kndemo.api.method.v1.MethodV1;
import com.example.kndemo.api.request.v1.CityPatchRequest;
import com.example.kndemo.services.CityService;
import org.springframework.stereotype.Component;

@Component
public class CityPatchMethod implements MethodV1<CityPatchRequest, Void> {

    private final CityService cityService;


    public CityPatchMethod(CityService cityService){
        this.cityService = cityService;
    }


    @Override
    public Void execute(CityPatchRequest request) {
        cityService.patchCity(getRequestMapper().cityPatchRequestToDto(request));
        return null;
    }
}
