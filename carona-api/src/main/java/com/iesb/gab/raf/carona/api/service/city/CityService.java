package com.iesb.gab.raf.carona.api.service.city;

import com.iesb.gab.raf.carona.api.dto.city.CityDto;
import com.iesb.gab.raf.carona.api.entity.city.City;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;

import java.util.List;

public interface CityService {
    List<CityDto> getCities();

    City getCityOrThrowException(Long id) throws ResourceNotFoundException;
}
