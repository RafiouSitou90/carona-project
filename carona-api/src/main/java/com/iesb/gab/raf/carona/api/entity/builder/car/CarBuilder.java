package com.iesb.gab.raf.carona.api.entity.builder.car;

import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.customer.Customer;

public final class CarBuilder {
    private final Car car;

    private CarBuilder() {
        car = new Car();
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public CarBuilder withModel(final String model) {
        car.setModel(model);

        return this;
    }

    public CarBuilder withLicensePlate(final String licensePlate) {
        car.setLicensePlate(licensePlate);

        return this;
    }

    public CarBuilder withColor(final String color) {
        car.setColor(color);

        return this;
    }

    public CarBuilder withNbSeats(final Integer nbSeats) {
        car.setNbSeats(nbSeats);

        return this;
    }

    public CarBuilder withDriver(final Customer driver) {
        car.setDriver(driver);

        return this;
    }

    public Car build() {
        return car;
    }
}
