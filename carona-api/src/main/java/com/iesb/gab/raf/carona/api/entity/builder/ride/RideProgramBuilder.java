package com.iesb.gab.raf.carona.api.entity.builder.ride;

import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.city.City;
import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;
import com.iesb.gab.raf.carona.api.entity.ride.RideReservation;

import java.math.BigDecimal;
import java.util.Set;

public final class RideProgramBuilder {
    private final RideProgram rideProgram;

    private RideProgramBuilder() {
        rideProgram = new RideProgram();
    }

    public static RideProgramBuilder builder() {
        return new RideProgramBuilder();
    }

    public RideProgramBuilder withCityFrom(final City cityFrom) {
        rideProgram.setCityFrom(cityFrom);

        return this;
    }

    public RideProgramBuilder withCityTo(final City cityTo) {
        rideProgram.setCityTo(cityTo);

        return this;
    }

    public RideProgramBuilder withCar(final Car car) {
        rideProgram.setCar(car);

        return this;
    }

    public RideProgramBuilder withDay(final String day) {
        rideProgram.setDay(day);

        return this;
    }

    public RideProgramBuilder withDepartureTime(final String departureTime) {
        rideProgram.setDepartureTime(departureTime);

        return this;
    }

    public RideProgramBuilder withIsActive(final Boolean isActive) {
        rideProgram.setIsActive(isActive);

        return this;
    }

    public RideProgramBuilder withIsFull(final Boolean isFull) {
        rideProgram.setIsFull(isFull);

        return this;
    }

    public RideProgramBuilder withPrice(final BigDecimal price) {
        rideProgram.setPrice(price);

        return this;
    }

    public RideProgramBuilder withTabRidesReservations(final Set<RideReservation> ridesReservations) {
        rideProgram.setRidesReservations(ridesReservations);

        return this;
    }

    public RideProgramBuilder withTabRidesReservations(final RideReservation ridesReservation) {
        if (rideProgram.getRidesReservations().contains(ridesReservation)) {
            return this;
        }
        rideProgram.getRidesReservations().add(ridesReservation);

        return this;
    }

    public RideProgram build() {
        return rideProgram;
    }
}
