package com.example.kndemo.jpa.mappers;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.dto.CityListDTO;
import com.example.kndemo.dto.PagedCityListDTO;
import com.example.kndemo.jpa.entities.City;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "url", source = "url")
    void patchCityFromDTO(CityDTO dto, @MappingTarget City entity);

    CityDTO cityToCityDTO(City city);

    default PagedCityListDTO createPagedCityListDTO(Page<City> page) {
        return PagedCityListDTO.builder()
                .cities(page.map(this::cityToCityDTO).getContent())
                .page(page.getNumber() + 1 ) // our page count will start from 1
                .totalPages(page.getTotalPages())
                .build();
    }

    List<CityDTO> mapCityLists(List<City> cities);

    default CityListDTO createCityListDTO(List<City> list) {
        return CityListDTO
                .builder()
                .cities(mapCityLists(list))
                .build();
    }
}
