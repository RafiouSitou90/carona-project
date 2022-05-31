package com.iesb.gab.raf.carona.api.repository.ride;

import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideProgramRepository extends JpaRepository<RideProgram, Long> {

    List<RideProgram> findAllByCar(final Car car);
}