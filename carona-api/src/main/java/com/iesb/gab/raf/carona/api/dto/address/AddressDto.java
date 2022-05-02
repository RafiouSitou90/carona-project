package com.iesb.gab.raf.carona.api.dto.address;

import com.iesb.gab.raf.carona.api.entity.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    protected String state;
    protected String city;
    protected String address;
    protected String postalCode;

    public AddressDto(final Address address) {
        state = address.getState();
        city = address.getCity();
        this.address = address.getAddress();
        postalCode = address.getPostalCode();
    }
}
