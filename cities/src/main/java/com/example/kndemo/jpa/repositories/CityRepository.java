package com.example.kndemo.jpa.repositories;

import com.example.kndemo.jpa.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, UUID> {
    List<City> findCityByNameStartingWithIgnoreCase(String name);

}
