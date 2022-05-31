package com.iesb.gab.raf.carona.api.dto.city;

import com.iesb.gab.raf.carona.api.entity.city.City;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    protected Long id;
    protected String name;

    public CityDto(final City city) {
        id = city.getId();
        name = city.getName();
    }
}
