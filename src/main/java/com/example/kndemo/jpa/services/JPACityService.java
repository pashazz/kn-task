package com.example.kndemo.jpa.services;

import com.example.kndemo.api.request.v1.CitiesGetRequest;
import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.dto.PagedCityListDTO;
import com.example.kndemo.exceptions.CityNotFoundException;
import com.example.kndemo.jpa.converters.Converters;
import com.example.kndemo.jpa.entities.City;
import com.example.kndemo.jpa.repositories.CityRepository;
import com.example.kndemo.response.v1.CitiesFindResponse;
import com.example.kndemo.response.v1.CitiesGetResponse;
import com.example.kndemo.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
public class JPACityService implements CityService {
    private final CityRepository repo;

    public JPACityService(@Autowired CityRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedCityListDTO getCities(int page, int size) {
        var data = repo.findAll(PageRequest.of(page,size));
        return Converters
                .pageToPagedCityListDTO(data);
    }

    @Override
    @Transactional(readOnly = true)
    public CityListDTO findCities(String query) {
        var data = repo.findCityByNameStartingWithIgnoreCase(query);
        return Converters
                .listToCityListDTO(data);
    }

    @Override
    @Transactional
    public void patchCity(CityDTO cityDTO) {
        Optional<City> cityOptional = repo.findById(cityDTO.getId());
        var city = cityOptional
                .orElseThrow(() -> new CityNotFoundException(cityDTO.getId()));
        repo.save(city);
    }
}
