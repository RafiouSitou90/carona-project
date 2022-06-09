package com.iesb.gab.raf.carona.api.dto.ride;

import com.iesb.gab.raf.carona.api.entity.ride.RideReservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideReservationDto extends RideReservationBaseDto {

    public RideReservationDto(final RideReservation rideReservation) {
        super(rideReservation);
    }
}
