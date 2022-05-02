package com.iesb.gab.raf.carona.api.repository.customer;

import com.iesb.gab.raf.carona.api.entity.customer.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Boolean existsByCpf(final String cpf);

    Boolean existsByPhoneNumber(final String phoneNumber);
}