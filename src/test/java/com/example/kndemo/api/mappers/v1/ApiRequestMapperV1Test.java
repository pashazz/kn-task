package com.example.kndemo.api.mappers.v1;

import com.example.kndemo.api.request.v1.CityPatchRequest;
import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ApiRequestMapperV1Test {

    private final ApiRequestMapperV1 sut = ApiRequestMapperV1.INSTANCE;

    @Test
    void shouldConvertPatchRequestToDto() {
        //arrange
        final var patchRequest = CityPatchRequest.builder()
                .id(TestUtils.UUID_2)
                .name(TestUtils.CITY_2)
                .photo(TestUtils.PHOTO_2)
                .build();

        final var expected = CityDTO.builder()
                .id(TestUtils.UUID_2)
                .name(TestUtils.CITY_2)
                .photo(TestUtils.PHOTO_2)
                .build();

        //exec
        var actual = sut.cityPatchRequestToDto(patchRequest);

        assertEquals(expected, actual);

    }

    @Test
    void shouldConvertPatchRequestToDtoWithNullName() {
                //arrange
        final var patchRequest = CityPatchRequest.builder()
                .id(TestUtils.UUID_2)
                .photo(TestUtils.PHOTO_2)
                .build();

        final var expected = CityDTO.builder()
                .id(TestUtils.UUID_2)
                .name(null)
                .photo(TestUtils.PHOTO_2)
                .build();

        //exec
        var actual = sut.cityPatchRequestToDto(patchRequest);

        assertEquals(expected, actual);
    }
}