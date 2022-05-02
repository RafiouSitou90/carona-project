package com.iesb.gab.raf.carona.api.event.customer;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;
import com.iesb.gab.raf.carona.api.event.EntityCreatedEvent;

import lombok.Getter;

@Getter
public class CustomerCreatedEvent extends EntityCreatedEvent<Customer> {

    private final String loginUrl;

    public CustomerCreatedEvent(Customer customer, String loginUrl, Object source) {
        super(customer, source);
        this.loginUrl = loginUrl;
    }
}
