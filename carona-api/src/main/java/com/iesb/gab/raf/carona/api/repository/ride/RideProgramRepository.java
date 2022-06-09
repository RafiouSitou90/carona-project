package com.iesb.gab.raf.carona.api.repository.ride;

import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.city.City;
import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RideProgramRepository extends JpaRepository<RideProgram, Long> {

    List<RideProgram> findAllByCar(final Car car);

    List<RideProgram> findAllByCityFromAndCityTo(City cityFrom, City cityTo);
    List<RideProgram> findAllByCityFromAndCityToAndDay(City cityFrom, City cityTo, String day);
    List<RideProgram> findAllByCityFromAndCityToAndDepartureTime(City cityFrom, City cityTo, String departureTime);
    List<RideProgram> findAllByCityFromAndCityToAndDayAndDepartureTime(City cityFrom, City cityTo, String day,
                                                                       String departureTime);
/*
    @Query("SELECT r FROM RideProgram r WHERE (r.cityFrom = ?1 AND r.cityTo = ?2) OR r.day = ?3 OR r.departureTime =" +
            " ?4")
    List<RideProgram> fullSearch(City cityFrom, City cityTo, String day, String time);
*/
}