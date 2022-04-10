package com.iesb.gab.raf.carona.api.event.customer;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

@Getter
public class CustomerAccountConfirmedEvent extends ApplicationEvent {
    private final Customer customer;

    public CustomerAccountConfirmedEvent(Customer customer, Object source) {
        super(source);
        this.customer = customer;
    }
}
