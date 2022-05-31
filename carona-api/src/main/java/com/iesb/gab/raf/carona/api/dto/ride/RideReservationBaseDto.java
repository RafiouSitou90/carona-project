package com.iesb.gab.raf.carona.api.dto.ride;

import com.iesb.gab.raf.carona.api.dto.AbstractBaseDto;
import com.iesb.gab.raf.carona.api.dto.customer.CustomerDto;
import com.iesb.gab.raf.carona.api.entity.ride.RideReservation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RideReservationBaseDto extends AbstractBaseDto implements Serializable {

    protected RideProgramDto rideProgram;
    protected CustomerDto customer;

    public RideReservationBaseDto(final RideReservation rideReservation) {
        super(rideReservation);
        rideProgram = new RideProgramDto(rideReservation.getRideProgram());
        customer = new CustomerDto(rideReservation.getCustomer());
    }
}
