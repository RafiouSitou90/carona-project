package com.iesb.gab.raf.carona.api.service.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideReservationDto;
import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;
import com.iesb.gab.raf.carona.api.entity.ride.RideReservation;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.exception.ResourceBadRequestException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.ride.RideReservationRequest;
import com.iesb.gab.raf.carona.api.repository.ride.RideProgramRepository;
import com.iesb.gab.raf.carona.api.repository.ride.RideReservationRepository;
import com.iesb.gab.raf.carona.api.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RideReservationServiceImpl implements RideReservationService {

    private final static String RESOURCE_NAME = "City";
    private final RideReservationRepository rideReservationRepository;
    private final RideProgramRepository rideProgramRepository;

    @Override
    public RideReservationDto saveReservation(RideReservationRequest rideReservationRequest) throws ResourceAlreadyExistsException, ResourceAlreadyExistsException {

        RideProgram rideProgram = getRideProgramOrThrowException(rideReservationRequest.getRideProgramId());

        if (!rideProgram.getIsActive() && rideProgram.getIsFull()) {
            throw new ResourceBadRequestException("Ride unavailable");
        }

        User user = Utils.getUserAuthenticated();

        if (user == null) {
            throw new RuntimeException("Login required");
        }

        if (rideReservationRepository.existsByRideProgramAndCustomer(rideProgram, user.getCustomer())) {
            throw new ResourceAlreadyExistsException("You already have a reservation for this ride");
        }

        RideReservation rideReservation = new RideReservation();
        rideReservation.setRideProgram(rideProgram);
        rideReservation.setCustomer(user.getCustomer());

        return new RideReservationDto(rideReservationRepository.save(rideReservation));
    }

    @Override
    public List<RideReservationDto> getAllReservationsByCustomer() {
        User user = Utils.getUserAuthenticated();

        if (user == null) {
            throw new RuntimeException("Login required");
        }

        List<RideReservation> rideReservations = rideReservationRepository.findAllByCustomer(user.getCustomer());
        List<RideReservationDto> rideReservationsDto = new ArrayList<>();

        rideReservations.forEach(rideReservation -> rideReservationsDto.add(new RideReservationDto(rideReservation)));

        return rideReservationsDto;
    }

    @Override
    public RideReservationDto getReservationByIdAndCustomer(Long id) throws ResourceNotFoundException {
        RideReservation rideReservation = getRideReservationOrThrowException(id);

        Utils.denyAccessUnlessGranted(
                rideReservation.getCustomer().getLogin(),
                "Access denied! You don't have access to delete this car"
        );

        return new RideReservationDto(rideReservation);
    }

    @Override
    public void deleteReservationByIdAndCustomer(Long id) throws ResourceNotFoundException {
        RideReservation rideReservation = getRideReservationOrThrowException(id);

        Utils.denyAccessUnlessGranted(
                rideReservation.getCustomer().getLogin(),
                "Access denied! You don't have access to delete this car"
        );

        rideReservationRepository.delete(rideReservation);
    }

    public RideProgram getRideProgramOrThrowException(Long id) throws ResourceNotFoundException {
        return rideProgramRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ride Program", "Id", id));
    }

    public RideReservation getRideReservationOrThrowException(Long id) throws ResourceNotFoundException {
        return rideReservationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ride Reservation", "Id", id));
    }
}
