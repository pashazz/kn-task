package com.example.kndemo.api.mappers.v1;

import com.example.kndemo.api.request.v1.CityPatchRequest;
import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.utils.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface ApiRequestMapperV1  {
    ApiRequestMapperV1 INSTANCE = Mappers.getMapper(ApiRequestMapperV1.class);

    CityDTO cityPatchRequestToDto(CityPatchRequest request);


}
