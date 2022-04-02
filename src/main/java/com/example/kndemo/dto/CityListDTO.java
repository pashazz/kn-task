package com.example.kndemo.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@EqualsAndHashCode
@ToString
public class CityListDTO {
    List<CityDTO> cities;
}
