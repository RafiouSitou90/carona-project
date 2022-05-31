package com.iesb.gab.raf.carona.api.dto.ride;

import com.iesb.gab.raf.carona.api.entity.ride.RideProgram;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideProgramDto extends RideProgramBaseDto {

    public RideProgramDto(final RideProgram rideProgram) {
        super(rideProgram);
    }
}
