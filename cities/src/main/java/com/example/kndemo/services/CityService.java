package com.example.kndemo.services;


import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.dto.PagedCityListDTO;

/**
 * Todo: I'm sure we can split this interface even more if ever need to,
 * to ensure interface segregation
 *
 */
public interface CityService {
    PagedCityListDTO getCities(int page, int size);
    CityListDTO findCities(String query);
    void patchCity(CityDTO cityDTO);

}
