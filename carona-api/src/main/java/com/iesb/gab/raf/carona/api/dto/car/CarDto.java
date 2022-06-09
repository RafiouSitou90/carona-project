package com.iesb.gab.raf.carona.api.dto.car;

import com.iesb.gab.raf.carona.api.dto.customer.CustomerDto;
import com.iesb.gab.raf.carona.api.entity.car.Car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto extends CarBaseDto  {

    protected CustomerDto driver;

    public CarDto(final Car car) {
        super(car);
        driver = new CustomerDto(car.getDriver());
    }
}
