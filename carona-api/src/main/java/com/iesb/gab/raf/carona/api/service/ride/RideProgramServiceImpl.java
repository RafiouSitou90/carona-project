package com.iesb.gab.raf.carona.api.service.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideProgramDto;
import com.iesb.gab.raf.carona.api.entity.builder.ride.RideProgramBuilder;
import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.city.City;
import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.ResourceBadRequestException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.ride.RideProgramRequest;
import com.iesb.gab.raf.carona.api.repository.ride.RideProgramRepository;
import com.iesb.gab.raf.carona.api.service.city.CityService;
import com.iesb.gab.raf.carona.api.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class RideProgramServiceImpl implements RideProgramService {

    private final static String RESOURCE_NAME = "Ride Program";

    private final CityService cityService;
    private final RideProgramRepository rideProgramRepository;

    @Override
    public RideProgramDto saveProgram(RideProgramRequest rideProgramRequest) {

        City cityFrom = cityService.getCityOrThrowException(rideProgramRequest.getCityFromId());
        City cityTo = cityService.getCityOrThrowException(rideProgramRequest.getCityToId());

        User user = Utils.getUserAuthenticated();

        if (user == null) {
            throw new RuntimeException("Login required");
        }

        Car car = user.getCustomer().getCar();
        if (car == null) {
            throw new ResourceBadRequestException("Car required");
        }

        RideProgram rideProgram = RideProgramBuilder.builder()
                .withCar(car)
                .withCityFrom(cityFrom)
                .withCityTo(cityTo)
                .withDay(rideProgramRequest.getDay())
                .withDepartureTime(rideProgramRequest.getDepartureTime())
                .build();

        return new RideProgramDto(rideProgramRepository.save(rideProgram));
    }

    @Override
    public List<RideProgramDto> getAllProgramsByDriver() {
        User user = Utils.getUserAuthenticated();

        if (user == null) {
            throw new RuntimeException("Login required");
        }

        Car car = user.getCustomer().getCar();
        if (car == null) {
            throw new ResourceBadRequestException("Car required");
        }

        List<RideProgram> ridePrograms = rideProgramRepository.findAllByCar(car);
        List<RideProgramDto> rideProgramsDto = new ArrayList<>();

        ridePrograms.forEach(rideProgram -> rideProgramsDto.add(new RideProgramDto(rideProgram)));

        return rideProgramsDto;
    }

    @Override
    public RideProgramDto updateProgram(Long id, RideProgramRequest rideProgramRequest) {
        RideProgram rideProgram = getRideProgramOrThrowException(id);

        Utils.denyAccessUnlessGranted(
                rideProgram.getCar().getDriver().getLogin(),
                "Access denied! You don't have access this ride program"
        );

        if (!Objects.equals(rideProgram.getCityFrom().getId(), rideProgramRequest.getCityFromId())) {
            City cityFrom = cityService.getCityOrThrowException(rideProgramRequest.getCityFromId());
            rideProgram.setCityFrom(cityFrom);
        }

        if (!Objects.equals(rideProgram.getCityTo().getId(), rideProgramRequest.getCityToId())) {
            City cityTo = cityService.getCityOrThrowException(rideProgramRequest.getCityToId());
            rideProgram.setCityTo(cityTo);
        }

        rideProgram.setDay(rideProgramRequest.getDay());
        rideProgram.setDepartureTime(rideProgramRequest.getDepartureTime());

        return new RideProgramDto(rideProgramRepository.save(rideProgram));
    }

    @Override
    public void deleteProgram(Long id) {
        RideProgram rideProgram = getRideProgramOrThrowException(id);

        Utils.denyAccessUnlessGranted(
                rideProgram.getCar().getDriver().getLogin(),
                "Access denied! You don't have access this ride program"
        );

        rideProgramRepository.delete(rideProgram);
    }

    @Override
    public List<RideProgramDto> search(Long from, Long to, String day, String time) {
        City cityFrom = cityService.getCityOrNull(from);
        City cityTo = cityService.getCityOrNull(to);
        List<RideProgram> ridePrograms;
        List<RideProgramDto> rideProgramsDto = new ArrayList<>();

        if (day == null && time == null) {
            ridePrograms = rideProgramRepository.findAllByCityFromAndCityTo(cityFrom, cityTo);
        } else if (day != null && time == null) {
            ridePrograms = rideProgramRepository.findAllByCityFromAndCityToAndDay(cityFrom, cityTo, day);
        } else if (day == null) {
            ridePrograms = rideProgramRepository
                    .findAllByCityFromAndCityToAndDepartureTime(cityFrom, cityTo, time);
        } else {
            ridePrograms = rideProgramRepository
                    .findAllByCityFromAndCityToAndDayAndDepartureTime(cityFrom, cityTo, day, time);
        }

        ridePrograms.forEach(rideProgram -> rideProgramsDto.add(new RideProgramDto(rideProgram)));

        return rideProgramsDto;
    }

    @Override
    public List<RideProgramDto> allRides() {
        List<RideProgram> ridePrograms = rideProgramRepository.findAll();
        List<RideProgramDto> rideProgramsDto = new ArrayList<>();

        ridePrograms.forEach(rideProgram -> rideProgramsDto.add(new RideProgramDto(rideProgram)));

        return rideProgramsDto;
    }

    private RideProgram getRideProgramOrThrowException(final Long id) {
        return rideProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "Id", id));
    }

}
