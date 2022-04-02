package com.example.kndemo.jpa.converters;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.jpa.entities.City;
import com.example.kndemo.jpa.mappers.CityMapper;
import com.example.kndemo.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CityMapperTest {

    private final CityMapper sut = CityMapper.INSTANCE;

    @Test
    void shouldConvertGoodCityEntity() {
        //test
        var actual = sut.cityToCityDTO(TestUtils.CITY_LIST.get(0));

        //assert
        assertEquals(TestUtils.CITY_DTO_LIST.get(0), actual);
    }

    @Test
    void shouldConvertCityPage() {
        final int PAGE_NO = 2; //page numbers start from 0
        final int PAGE_SIZE = TestUtils.CITY_LIST.size();
        final int TOTAL_PAGES = 3; //total count starts from 1, i.e. this is the last page

        //arrange
        final var PAGE = new PageImpl<City>(TestUtils.CITY_LIST, PageRequest.of(PAGE_NO, PAGE_SIZE), TOTAL_PAGES);
        var actual = sut.createPagedCityListDTO(PAGE);

        //assert
        assertThat(actual.getCities()).isEqualTo(TestUtils.CITY_DTO_LIST);
        assertEquals(PAGE_NO + 1, actual.getPage());
        assertEquals(TOTAL_PAGES, actual.getTotalPages());

    }

    @Test
    void shouldCreateCityListDTO() {
        //test
        var actual = sut.createCityListDTO(TestUtils.CITY_LIST);

        assertThat(actual.getCities()).isEqualTo(TestUtils.CITY_DTO_LIST);
    }

    @Test
    void shouldPatchCity() {
        //Arrange
        City city = new City(TestUtils.UUID_1, TestUtils.CITY_1, TestUtils.PHOTO_1);
        CityDTO change = new CityDTO(TestUtils.UUID_1, TestUtils.CITY_2, null);

        //Test
        sut.patchCityFromDTO(change, city);

        //Assert
        assertEquals(TestUtils.UUID_1, city.getId());
        assertEquals(TestUtils.CITY_2, city.getName());
        assertEquals(TestUtils.PHOTO_1, city.getPhoto()); // not changed
    }


    // region null check tests\
    /**
    @Test
    void shouldFailWhenIdIsNull() {
        final City TEST_OBJECT = new City(null, "Belgorod", "https://example.com/belgorod.jpg");
        assertThrows(NullPointerException.class, () ->
                sut.cityToCityDTO(TEST_OBJECT));
    }

    @Test
    void shouldFailWhenNameIsNull() {
        final City TEST_OBJECT = new City(TestUtils.UUID_1, null, "https://example.com/belgorod.jpg");
        assertThrows(NullPointerException.class, () ->
                sut.cityToCityDTO(TEST_OBJECT));
    }
    @Test
    void shouldFailWhenPhotoIsNull() {
        final City TEST_OBJECT = new City(TestUtils.UUID_1, "Belgorod", null);
        assertThrows(NullPointerException.class, () ->
                sut.cityToCityDTO(TEST_OBJECT));
    }
    **/
    //endregion
}