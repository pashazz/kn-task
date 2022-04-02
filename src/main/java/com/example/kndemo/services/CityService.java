package com.example.kndemo.services;


import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.dto.PagedCityListDTO;

public interface CityService {
    PagedCityListDTO getCities(int page, int size);
    CityListDTO findCities(String query);
    void patchCity(CityDTO cityDTO);

}
