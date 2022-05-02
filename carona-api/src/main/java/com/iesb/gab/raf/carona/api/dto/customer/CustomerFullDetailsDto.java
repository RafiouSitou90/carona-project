package com.iesb.gab.raf.carona.api.dto.customer;

import com.iesb.gab.raf.carona.api.dto.user.UserDto;
import com.iesb.gab.raf.carona.api.entity.customer.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFullDetailsDto extends CustomerBaseDto {

    protected UserDto identifier;

    public CustomerFullDetailsDto(final Customer customer) {
        super(customer);
        identifier = new UserDto(customer.getLogin());
    }
}
