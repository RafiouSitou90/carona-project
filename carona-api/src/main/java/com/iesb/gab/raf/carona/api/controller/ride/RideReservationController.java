package com.iesb.gab.raf.carona.api.controller.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideReservationDto;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.payload.request.ride.RideReservationRequest;
import com.iesb.gab.raf.carona.api.service.ride.RideReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/rides/reservations")
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_USER', 'ROLE_DRIVER')")
@AllArgsConstructor
public class RideReservationController {

    private final RideReservationService rideReservationService;

    @PostMapping
    public ResponseEntity<RideReservationDto> saveReservation(@RequestBody @Valid RideReservationRequest rideReservationRequest) throws ResourceAlreadyExistsException,
            ResourceAlreadyExistsException {
        return new ResponseEntity<>(rideReservationService.saveReservation(rideReservationRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RideReservationDto>> getAllReservationsByCustomer() {
        return new ResponseEntity<>(rideReservationService.getAllReservationsByCustomer(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RideReservationDto> getReservationByIdAndCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(rideReservationService.getReservationByIdAndCustomer(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteReservationByIdAndCustomer(@PathVariable("id") Long id) {
        rideReservationService.deleteReservationByIdAndCustomer(id);

        return new ResponseEntity<>("Ride Reservation deleted successfully.", HttpStatus.OK);
    }
}
