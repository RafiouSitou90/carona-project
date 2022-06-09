package com.iesb.gab.raf.carona.api.controller.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideProgramDto;
import com.iesb.gab.raf.carona.api.payload.request.ride.RideProgramRequest;
import com.iesb.gab.raf.carona.api.service.ride.RideProgramService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/rides/programs")
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_USER', 'ROLE_DRIVER')")
@AllArgsConstructor
public class RideProgramController {

    private final RideProgramService rideProgramService;

    @PostMapping
    public ResponseEntity<RideProgramDto> saveProgram(@RequestBody @Valid RideProgramRequest rideProgramRequest) {
        return new ResponseEntity<>(rideProgramService.saveProgram(rideProgramRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RideProgramDto> updateProgram(@PathVariable("id") Long id,
                                                         @RequestBody @Valid RideProgramRequest rideProgramRequest) {
        return new ResponseEntity<>(rideProgramService.updateProgram(id, rideProgramRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RideProgramDto>> getAllProgramsByDriver() {
        return new ResponseEntity<>(rideProgramService.getAllProgramsByDriver(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RideProgramDto> getProgramsByDriverAndById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(rideProgramService.getProgramsByDriverAndById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProgram(@PathVariable("id") Long id) {
        rideProgramService.deleteProgram(id);

        return new ResponseEntity<>("Ride Program deleted successfully.", HttpStatus.OK);
    }
}
