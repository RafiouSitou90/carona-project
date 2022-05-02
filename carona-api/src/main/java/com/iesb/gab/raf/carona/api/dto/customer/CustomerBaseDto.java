package com.iesb.gab.raf.carona.api.dto.customer;

import com.iesb.gab.raf.carona.api.dto.AbstractBaseDto;
import com.iesb.gab.raf.carona.api.dto.address.AddressDto;
import com.iesb.gab.raf.carona.api.entity.customer.Customer;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerBaseDto extends AbstractBaseDto implements Serializable {

    protected String cpf;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String phoneNumber;
    protected String avatar;
    protected AddressDto address;

    public CustomerBaseDto(final Customer customer) {
        super(customer);
        cpf = customer.getCpf();
        email = customer.getLogin().getEmail();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        gender = customer.getGender();
        phoneNumber = customer.getPhoneNumber();
        avatar = customer.getAvatar();
        address = new AddressDto(customer.getAddress());
    }
}
