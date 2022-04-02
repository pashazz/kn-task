package com.example.kndemo.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@EqualsAndHashCode
@ToString
public class CityListDTO {
    @NonNull
    List<CityDTO> cities;
}
