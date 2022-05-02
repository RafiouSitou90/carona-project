package com.iesb.gab.raf.carona.api.entity.builder.customer;

import com.iesb.gab.raf.carona.api.entity.address.Address;
import com.iesb.gab.raf.carona.api.entity.customer.Customer;
import com.iesb.gab.raf.carona.api.entity.user.User;

public final class CustomerBuilder {

    private final Customer customer;

    private CustomerBuilder() {
        customer = new Customer();
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withCpf(final String cpf) {
        customer.setCpf(cpf);

        return this;
    }

    public CustomerBuilder withFirstName(final String firstName) {
        customer.setFirstName(firstName);

        return this;
    }

    public CustomerBuilder withLastName(final String lastName) {
        customer.setLastName(lastName);

        return this;
    }

    public CustomerBuilder withGender(final String gender) {
        customer.setGender(gender);
        return this;
    }

    public CustomerBuilder withPhoneNumber(final String phoneNumber) {
        customer.setPhoneNumber(phoneNumber);

        return this;
    }

    public CustomerBuilder withAvatar(final String avatar) {
        customer.setAvatar(avatar);

        return this;
    }

    public CustomerBuilder withLogin(final User login) {
        customer.setLogin(login);

        return this;
    }

    public CustomerBuilder withAddress(final Address address) {
        customer.setAddress(address);

        return this;
    }

    public Customer build() {
        return customer;
    }
}
