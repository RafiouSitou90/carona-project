package com.iesb.gab.raf.carona.api.dto.car;

import com.iesb.gab.raf.carona.api.dto.AbstractBaseDto;

import com.iesb.gab.raf.carona.api.entity.car.Car;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CarBaseDto extends AbstractBaseDto implements Serializable {

    protected String model;
    protected String licensePlate;
    protected String color;
    protected Integer nbSeats;

    public CarBaseDto(final Car car) {
        super(car);
        model = car.getModel();
        licensePlate = car.getLicensePlate();
        color = car.getColor();
        nbSeats = car.getNbSeats();
    }
}
