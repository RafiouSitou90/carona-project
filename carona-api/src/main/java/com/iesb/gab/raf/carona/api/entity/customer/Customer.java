package com.iesb.gab.raf.carona.api.entity.customer;

import com.iesb.gab.raf.carona.api.entity.AbstractBaseEntity;
import com.iesb.gab.raf.carona.api.entity.address.Address;
import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_customers", indexes = {
        @Index(name = "customer_cpf_uk", columnList = "cpf", unique = true),
        @Index(name = "customer_phone_number_uk", columnList = "phone_number", unique = true)
})
public class Customer extends AbstractBaseEntity {

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "avatar")
    private String avatar;

    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id", nullable = false)
    private User login;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "driver")
    private Car car;

    public String getFullName() {
        if (firstName == null && lastName == null) {
            return null;
        }

        if (firstName != null && lastName != null) {
            return String.format("%s %s", firstName, lastName);
        }

        if (firstName != null) {
            return firstName;
        }

        return lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cpf='" + cpf + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;

        return getId() != null && Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}