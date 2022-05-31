package com.iesb.gab.raf.carona.api.service.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideProgramDto;
import com.iesb.gab.raf.carona.api.payload.request.ride.RideProgramRequest;

import java.util.List;

public interface RideProgramService {

    RideProgramDto saveProgram(RideProgramRequest rideProgramRequest);

    List<RideProgramDto> getAllProgramsByDriver();

    RideProgramDto updateProgram(Long id, RideProgramRequest rideProgramRequest);

    void deleteProgram(Long id);

    List<RideProgramDto> search(Long from, Long to, String day, String time);

    List<RideProgramDto> allRides();
}
