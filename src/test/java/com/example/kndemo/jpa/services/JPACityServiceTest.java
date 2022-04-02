package com.example.kndemo.jpa.services;

import com.example.kndemo.jpa.entities.City;
import com.example.kndemo.jpa.repositories.CityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JPACityServiceTest {

    @Mock
    private CityRepository repo;

    private JPACityService sut;

    private Page<City> testPage;
    private List<City> cityData;

    private List<UUID> validUuids = List.of(
            UUID.fromString("dc60752e-9345-4da7-9c04-3197d8996b40"),
            UUID.fromString("bef39dd4-274f-44a2-ae9d-6b5a19b41620"),
            UUID.fromString("3088cdf9-4c51-4e10-9594-a75f36140d2a"));


    @BeforeEach
    void setUp() {
        sut = new JPACityService(repo);
    }

    @Test
    public void getCitiesReturnValidData() {
        //ARRANGE
        cityData = new ArrayList<>();
        cityData.add(new City(validUuids.get(0), "Moscow", "moscow.jpg"));
        //cityData.add(new City(validUuids.get(1), "Tallinn", "tallinn.jpg"));
        //cityData.add(new City(validUuids.get(2), "Riga", "riga.jpg"));

        testPage = new PageImpl<City>(cityData);


        when(repo.findAll(PageRequest.of(0, 1))).thenReturn(testPage);

        //Execute
        var result = sut.getCities(0, 1);

        //Verify
        verify(repo, times(1)).findAll(PageRequest.of(0, 1));


        //Assert
        var city = result.getCities().get(0);
        assertEquals(cityData.get(0).getId(), city.getId());
        assertEquals(cityData.get(0).getName(), city.getName());
        assertEquals(cityData.get(0).getPhoto(), city.getPhoto());




    }


}