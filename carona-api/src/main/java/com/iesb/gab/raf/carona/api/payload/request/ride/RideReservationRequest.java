package com.iesb.gab.raf.carona.api.payload.request.ride;

import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public final class RideReservationRequest {

    @PositiveOrZero(message = "The ride program id must be greater than or equal to zero")
    private Long rideProgramId;
}
