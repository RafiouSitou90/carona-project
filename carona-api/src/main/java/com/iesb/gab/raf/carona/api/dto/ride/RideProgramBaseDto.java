package com.iesb.gab.raf.carona.api.dto.ride;

import com.iesb.gab.raf.carona.api.dto.AbstractBaseDto;

import com.iesb.gab.raf.carona.api.dto.car.CarDto;
import com.iesb.gab.raf.carona.api.dto.city.CityDto;
import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RideProgramBaseDto extends AbstractBaseDto implements Serializable {

    protected CityDto cityFrom;
    protected CityDto cityTo;
    protected CarDto car;
    protected String day;
    protected String departureTime;
    protected Boolean isActive;
    protected Boolean isFull;

    public RideProgramBaseDto(final RideProgram rideProgram) {
        super(rideProgram);
        cityFrom = new CityDto(rideProgram.getCityFrom());
        cityTo = new CityDto(rideProgram.getCityTo());
        car = new CarDto(rideProgram.getCar());
        day = rideProgram.getDay();
        departureTime = rideProgram.getDepartureTime();
        isActive = rideProgram.getIsActive();
        isFull = rideProgram.getIsFull();
    }
}
