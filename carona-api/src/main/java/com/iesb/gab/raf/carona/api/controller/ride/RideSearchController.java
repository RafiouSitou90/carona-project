package com.iesb.gab.raf.carona.api.controller.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideProgramDto;
import com.iesb.gab.raf.carona.api.service.ride.RideProgramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rides")
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_USER', 'ROLE_DRIVER')")
@AllArgsConstructor
public class RideSearchController {

    private final RideProgramService rideProgramService;

    @GetMapping
    public ResponseEntity<List<RideProgramDto>> allRides() {
        return new ResponseEntity<>(rideProgramService.allRides(), HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<RideProgramDto>> search(@RequestParam Long from,
                                                       @RequestParam Long to,
                                                       @RequestParam(required = false) String day,
                                                       @RequestParam(required = false) String time) {
        return new ResponseEntity<>(rideProgramService.search(from, to, day, time), HttpStatus.OK);
    }
}
