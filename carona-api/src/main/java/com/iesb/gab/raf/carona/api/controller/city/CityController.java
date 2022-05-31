package com.iesb.gab.raf.carona.api.controller.city;

import com.iesb.gab.raf.carona.api.dto.city.CityDto;
import com.iesb.gab.raf.carona.api.service.city.CityService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getCities() {
        return new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
    }
}
