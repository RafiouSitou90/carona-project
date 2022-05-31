package com.iesb.gab.raf.carona.api.controller.car;

import com.iesb.gab.raf.carona.api.dto.car.CarDto;
import com.iesb.gab.raf.carona.api.exception.ResourceAccessDenied;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.car.CarRequest;
import com.iesb.gab.raf.carona.api.service.car.CarService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/cars")
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_USER', 'ROLE_DRIVER')")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDto> save(@RequestBody @Valid CarRequest carRequest) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(carService.save(carRequest), HttpStatus.CREATED);
    }

//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException,
//            ResourceAccessDenied {
//        carService.delete(id);
//
//        return new ResponseEntity<>("Driver Car successfully deleted", HttpStatus.OK);
//    }
}
