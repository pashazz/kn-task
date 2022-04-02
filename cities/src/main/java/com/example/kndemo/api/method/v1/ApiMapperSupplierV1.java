package com.example.kndemo.api.method.v1;

import com.example.kndemo.api.mappers.v1.ApiRequestMapperV1;
import com.example.kndemo.api.mappers.v1.ApiResponseMapperV1;
import com.example.kndemo.api.method.ApiMapperSupplier;

public interface ApiMapperSupplierV1 extends ApiMapperSupplier<ApiRequestMapperV1, ApiResponseMapperV1> {
    @Override
    default ApiResponseMapperV1 getResponseMapper() {
        return ApiResponseMapperV1.INSTANCE;
    }

    @Override
    default ApiRequestMapperV1 getRequestMapper() {
        return ApiRequestMapperV1.INSTANCE;
    }
}
