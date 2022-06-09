package com.iesb.gab.raf.carona.api.repository.car;

import com.iesb.gab.raf.carona.api.entity.car.Car;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    Boolean existsByLicensePlateIgnoreCase(final String licensePlate);
}