package com.iesb.gab.raf.carona.api.controller.ride;

import com.iesb.gab.raf.carona.api.dto.ride.RideProgramDto;
import com.iesb.gab.raf.carona.api.service.ride.RideProgramService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rides/search")
@PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_USER', 'ROLE_DRIVER')")
@AllArgsConstructor
public class RideSearchController {

    private final RideProgramService rideProgramService;

//    public ResponseEntity<List<RideProgramDto>> search() {
//
//    }
}
