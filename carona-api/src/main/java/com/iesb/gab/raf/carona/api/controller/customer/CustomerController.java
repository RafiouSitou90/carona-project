package com.iesb.gab.raf.carona.api.controller.customer;

import com.iesb.gab.raf.carona.api.dto.customer.CustomerFullDetailsDto;
import com.iesb.gab.raf.carona.api.payload.request.customer.CustomerCreateRequest;
import com.iesb.gab.raf.carona.api.service.customer.CustomerService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
