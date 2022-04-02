package com.example.kndemo.jpa.services;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.exceptions.CityNotFoundException;
import com.example.kndemo.jpa.entities.City;
import com.example.kndemo.jpa.repositories.CityRepository;
import com.example.kndemo.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class JPACityServiceTest {

    @Mock
    private CityRepository repo;

    private JPACityService sut;


    @BeforeEach
    void setUp() {
        sut = new JPACityService(repo);
    }

    @Test
    public void getCitiesReturnValidData() {
        //ARRANGE
        var cityData = new ArrayList<City>();
        cityData.add(TestUtils.CITY_LIST.get(0));

        var testPage = new PageImpl<>(cityData);

        when(repo.findAll(PageRequest.of(0, 1))).thenReturn(testPage);

        //Execute
        var result = sut.getCities(0, 1);

        //Verify
        verify(repo, times(1)).findAll(PageRequest.of(0, 1));

        //Assert
        var city = result.getCities().get(0);
        assertEquals(cityData.get(0).getId(), city.getId());
        assertEquals(cityData.get(0).getName(), city.getName());
        assertEquals(cityData.get(0).getUrl(), city.getUrl());

    }

    @Test
    public void findCitiesReturnValidData() {
        //ARRANGE
        var cityData = new ArrayList<City>();
        cityData.add(TestUtils.CITY_LIST.get(0));

        var ARG = "mos";

        when(repo.findCityByNameStartingWithIgnoreCase(ARG)).thenReturn(cityData);

        //Execute
        CityListDTO result = sut.findCities(ARG);

        //Verify
        verify(repo, times(1)).findCityByNameStartingWithIgnoreCase(ARG);

        assertThat(result.getCities()).containsExactly(TestUtils.CITY_DTO_LIST.get(0));

    }

    @Test
    public void patchCityReturnOKWithValidUUID() {
        City cityToBePatched = new City(TestUtils.UUID_1, TestUtils.CITY_1, TestUtils.PHOTO_1);

        when(repo.findById(any())).thenReturn(Optional.empty());
        when(repo.findById(cityToBePatched.getId())).thenReturn(Optional.of(cityToBePatched));


        CityDTO patch = CityDTO.builder()
                .id(TestUtils.UUID_1)
                .name(TestUtils.CITY_2)
                .url(null)
                .build();

        //execute
        sut.patchCity(patch);

        //verify
        verify(repo, times(1)).findById(patch.getId());
        verify(repo, times(1)).save(cityToBePatched);
        verify(repo, times(0)).save(Mockito.argThat(city -> city.getId() != TestUtils.UUID_1));

    }

    @Test
    public void patchCityThrowsWithNotFoundUUID() {
        when(repo.findById(any())).thenReturn(Optional.empty());


        CityDTO patch = CityDTO.builder()
                .id(TestUtils.UUID_2)
                .name(TestUtils.CITY_2)
                .url(null)
                .build();

        //execute
        assertThrows(CityNotFoundException.class, () -> sut.patchCity(patch));

        //verify
        verify(repo, times(1)).findById(patch.getId());
        verify(repo, times(0)).save(any());

    }



}