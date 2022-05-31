package com.iesb.gab.raf.carona.api.service.city;

import com.iesb.gab.raf.carona.api.dto.city.CityDto;
import com.iesb.gab.raf.carona.api.entity.city.City;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.repository.city.CityRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final static String RESOURCE_NAME = "City";

    private final CityRepository cityRepository;

    @Override
    public List<CityDto> getCities() {
        List<City> cities = cityRepository.findAll(Sort.by(Sort.Order.asc("name")));
        List<CityDto> citiesDto = new ArrayList<>();
        cities.forEach(city -> citiesDto.add(new CityDto(city)));

        return citiesDto;
    }

    public City getCityOrThrowException(Long id) throws ResourceNotFoundException {
        return cityRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "Id", id));
    }

    @Override
    public City getCityOrNull(Long id) {
        Optional<City> city = cityRepository.findById(id);

        if (city.isEmpty()) {
            return null;
        }

        return city.get();
    }
}
