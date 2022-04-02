package com.example.kndemo.api.mappers.v1;

import com.example.kndemo.api.response.v1.CitiesGetResponse;
import com.example.kndemo.dto.PagedCityListDTO;
import com.example.kndemo.utils.TestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseMapperV1Test {

    private final ApiResponseMapperV1 sut = ApiResponseMapperV1.INSTANCE;

    @Test
    void shouldConvertCityDTOToResponse() {
        //arrange
        final var dto = TestUtils.CITY_DTO_LIST.get(0);
        final var expected = TestUtils.CITY_RESPONSE_LIST_V1.get(0);

        //execute
        var actual = sut.cityDtoToResponse(dto);

        //assert
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void shouldConvertPagedCityDTOToGetResponse() {
        //arrange
        final var dto = PagedCityListDTO.builder()
                .cities(TestUtils.CITY_DTO_LIST)
                .page(1)
                .totalPages(1)
                .build();

        final var expected =
                CitiesGetResponse.builder()
                        .cities(TestUtils.CITY_RESPONSE_LIST_V1)
                        .page(1)
                        .totalPages(1)
                        .build();

        //exec
        var actual = sut.dtoToGetResponse(dto);

        //assert
        assertThat(actual).isEqualTo(expected);
    }


}