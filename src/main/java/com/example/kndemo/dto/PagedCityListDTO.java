package com.example.kndemo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
public class PagedCityListDTO extends CityListDTO {
    int page;
    int totalPages;
}
