package com.example.kndemo.api.mappers.v1;

import com.example.kndemo.api.response.v1.CitiesFindResponse;
import com.example.kndemo.api.response.v1.CitiesGetResponse;
import com.example.kndemo.api.response.v1.CityResponse;
import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.dto.PagedCityListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApiResponseMapperV1 {
    ApiResponseMapperV1 INSTANCE = Mappers.getMapper(ApiResponseMapperV1.class);

    CityResponse cityDtoToResponse(CityDTO dto);

    CitiesGetResponse dtoToGetResponse(PagedCityListDTO dto);

    CitiesFindResponse dtoToFindResponse(CityListDTO dto);
}
