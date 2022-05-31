package com.iesb.gab.raf.carona.api.repository.city;

import com.iesb.gab.raf.carona.api.entity.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
}