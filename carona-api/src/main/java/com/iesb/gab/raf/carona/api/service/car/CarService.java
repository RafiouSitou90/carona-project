package com.iesb.gab.raf.carona.api.service.car;

import com.iesb.gab.raf.carona.api.dto.car.CarDto;
import com.iesb.gab.raf.carona.api.exception.ResourceAccessDenied;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.car.CarRequest;

public interface CarService {

    String[] ROLE_DRIVERS = {"ROLE_DRIVER"};

    CarDto save(CarRequest carRequest) throws ResourceAlreadyExistsException;

    void delete(Long id) throws ResourceNotFoundException, ResourceAccessDenied;
}
