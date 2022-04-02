package com.example.kndemo.jpa.mappers;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.jpa.entities.City;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCityFromDTO(CityDTO dto, @MappingTarget City entity);

}
