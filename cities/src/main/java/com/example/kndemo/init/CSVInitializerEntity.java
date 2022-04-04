package com.example.kndemo.init;

import com.example.kndemo.jpa.entities.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder
@Jacksonized
@Value
public class CSVInitializerEntity {
    String id;
    String name;
    String photo;

    // Do not save ID
    City toCity() {
        City city = new City();
        city.setName(getName());
        city.setUrl(getPhoto());
        return city;
    }
}
