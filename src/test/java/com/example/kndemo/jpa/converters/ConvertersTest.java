package com.example.kndemo.jpa.converters;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.jpa.entities.City;
import com.example.kndemo.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ConvertersTest {



    @Test
    void shouldConvertGoodCityEntity() {
        //test
        var actual = Converters.cityToCityDTO(TestUtils.CITY_LIST.get(0));

        //assert
        assertEquals(TestUtils.CITY_DTO_LIST.get(0), actual);
    }

    @Test
    void shouldConvertCityPage() {
        //arrange
        final var PAGE = new PageImpl<City>(TestUtils.CITY_LIST);
        //var actual = Converters.pageToPagedCityListDTO()
    }

    // region null check tests
    @Test
    void shouldFailWhenIdIsNull() {
        final City TEST_OBJECT = new City(null, "Belgorod", "https://example.com/belgorod.jpg");
        assertThrows(NullPointerException.class, () ->
                Converters.cityToCityDTO(TEST_OBJECT));
    }

    @Test
    void shouldFailWhenNameIsNull() {
        final City TEST_OBJECT = new City(TestUtils.UUID_1, null, "https://example.com/belgorod.jpg");
        assertThrows(NullPointerException.class, () ->
                Converters.cityToCityDTO(TEST_OBJECT));
    }
    @Test
    void shouldFailWhenPhotoIsNull() {
        final City TEST_OBJECT = new City(TestUtils.UUID_1, "Belgorod", null);
        assertThrows(NullPointerException.class, () ->
                Converters.cityToCityDTO(TEST_OBJECT));
    }
    //endregion
}