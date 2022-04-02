package com.example.kndemo.utils;

import com.example.kndemo.dto.CityDTO;
import com.example.kndemo.jpa.entities.City;

import java.util.List;
import java.util.UUID;

public class TestUtils {
    public static final UUID UUID_1 = UUID.fromString("dc60752e-9345-4da7-9c04-3197d8996b40");
    public static final UUID UUID_2  = UUID.fromString("bef39dd4-274f-44a2-ae9d-6b5a19b41620");

    public static final String CITY_1 = "Moscow";
    public static final String CITY_2 = "Riga";

    public static final String PHOTO_1 = "moscow.jpg";
    public static final String PHOTO_2 = "riga.jpg";

    public static final List<City> CITY_LIST = List.of(
            new City(UUID_1, CITY_1, PHOTO_1),
            new City(UUID_2, CITY_2, PHOTO_2)
    );

    public static final List<CityDTO> CITY_DTO_LIST = List.of(
            new CityDTO(UUID_1, CITY_1, PHOTO_1),
            new CityDTO(UUID_2, CITY_2, PHOTO_2)
    );



}
