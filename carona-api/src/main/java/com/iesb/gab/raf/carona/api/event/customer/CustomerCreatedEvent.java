package com.iesb.gab.raf.carona.api.event.customer;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;
import com.iesb.gab.raf.carona.api.event.EntityCreatedEvent;

public class CustomerCreatedEvent extends EntityCreatedEvent<Customer> {

    public CustomerCreatedEvent(Customer customer, Object source) {
        super(customer, source);
    }
}
