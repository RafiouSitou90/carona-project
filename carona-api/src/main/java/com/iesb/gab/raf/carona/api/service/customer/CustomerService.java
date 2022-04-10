package com.iesb.gab.raf.carona.api.service.customer;

import com.iesb.gab.raf.carona.api.dto.customer.CustomerFullDetailsDto;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.payload.request.customer.CustomerCreateRequest;

public interface CustomerService {

    String[] ROLE_CUSTOMERS = {"ROLE_USER", "ROLE_CUSTOMER"};

    CustomerFullDetailsDto save(CustomerCreateRequest customerCreateRequest) throws ResourceAlreadyExistsException;

    void confirmAccount(String token);
}
