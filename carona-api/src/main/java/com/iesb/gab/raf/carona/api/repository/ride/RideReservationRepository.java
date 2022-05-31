package com.iesb.gab.raf.carona.api.repository.ride;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;
import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;
import com.iesb.gab.raf.carona.api.entity.ride.RideReservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideReservationRepository extends JpaRepository<RideReservation, Long> {

    Boolean existsByRideProgramAndCustomer(final RideProgram rideProgram, final Customer customer);

    List<RideReservation> findAllByCustomer(final Customer customer);
}