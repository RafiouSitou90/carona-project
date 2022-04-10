package com.iesb.gab.raf.carona.api.controller.customer;

import com.iesb.gab.raf.carona.api.dto.customer.CustomerFullDetailsDto;
import com.iesb.gab.raf.carona.api.payload.request.customer.CustomerCreateRequest;
import com.iesb.gab.raf.carona.api.service.customer.CustomerService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerFullDetailsDto> save(@RequestBody @Valid CustomerCreateRequest customerCreateRequest) {
        return new ResponseEntity<>(customerService.save(customerCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/confirm/email") // GET http://localhost:8080/api/v1/customers/confirm/email?token=
    public ResponseEntity<String> confirmAccount(@RequestParam String token) {
        customerService.confirmAccount(token);

        return new ResponseEntity<>("Customer account successfully confirmed.", HttpStatus.OK);
    }
}
