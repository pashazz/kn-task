package com.example.kndemo.jpa.services;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.dto.PagedCityListDTO;
import com.example.kndemo.exceptions.CityNotFoundException;
import com.example.kndemo.jpa.entities.City;
import com.example.kndemo.jpa.mappers.CityMapper;
import com.example.kndemo.jpa.repositories.CityRepository;
import com.example.kndemo.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
public class JPACityService implements CityService {
    private final CityRepository repo;
    private final CityMapper cityMapper;

    @Autowired
    public JPACityService(@Autowired CityRepository repo) {
        this(repo, CityMapper.INSTANCE);
    }

    public JPACityService(CityRepository repo, CityMapper cityMapper){
        this.repo = repo;
        this.cityMapper = cityMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedCityListDTO getCities(int page, int size) {
        log.info("getCities: page: {}: size: {}", page, size);
        var data = repo.findAll(PageRequest.of(page,size));
        log.info("getCities: obtained {} elements on page {}", data.getNumberOfElements(), data.getNumber());
        return cityMapper.createPagedCityListDTO(data);
    }

    @Override
    @Transactional(readOnly = true)
    public CityListDTO findCities(String query) {
        log.info("findCities: searching by query: {}", query);
        var data = repo.findCityByNameStartingWithIgnoreCase(query);
        log.info("findCities: found entries: {}", data.size());
        return cityMapper.createCityListDTO(data);
    }

    @Override
    @Transactional
    public void patchCity(CityDTO cityDTO) {
        Optional<City> cityOptional = repo.findById(cityDTO.getId());
        var city = cityOptional
                .orElseThrow(() -> new CityNotFoundException(cityDTO.getId()));
        cityMapper.patchCityFromDTO(cityDTO, city);
        repo.save(city);
    }
}
