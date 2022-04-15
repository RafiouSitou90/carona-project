package com.iesb.gab.raf.carona.api.dto.customer;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto extends CustomerBaseDto {

    public CustomerDto(final Customer customer) {
        super(customer);
    }
}
