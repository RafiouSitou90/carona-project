package com.iesb.gab.raf.carona.api.service.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideReservationDto;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.ride.RideReservationRequest;

import java.util.List;

public interface RideReservationService {
    RideReservationDto saveReservation(RideReservationRequest rideReservationRequest) throws ResourceAlreadyExistsException, ResourceNotFoundException;

    List<RideReservationDto> getAllReservationsByCustomer();

    RideReservationDto getReservationByIdAndCustomer(Long id) throws ResourceNotFoundException;

    void deleteReservationByIdAndCustomer(Long id) throws ResourceNotFoundException;
}
