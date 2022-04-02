package com.example.kndemo.jpa.converters;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.dto.PagedCityListDTO;
import com.example.kndemo.jpa.entities.City;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Converters {
    public static PagedCityListDTO pageToPagedCityListDTO(Page<City> page) {
        return PagedCityListDTO.builder()
                .cities(page
                        .map(Converters::cityToCityDTO)
                        .getContent())
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .build();

    }

    private static CityDTO cityToCityDTO(City city) {
        return CityDTO.builder()
                .name(city.getName())
                .photo(city.getPhoto())
                .build();

    }
    public static CityListDTO listToCityListDTO(List<City> list) {
        return CityListDTO.builder()
                .cities(list
                        .stream()
                        .map(Converters::cityToCityDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
