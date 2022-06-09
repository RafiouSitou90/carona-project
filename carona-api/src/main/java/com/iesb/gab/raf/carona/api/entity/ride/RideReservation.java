package com.iesb.gab.raf.carona.api.entity.ride;

import com.iesb.gab.raf.carona.api.entity.AbstractBaseEntity;
import com.iesb.gab.raf.carona.api.entity.city.City;
import com.iesb.gab.raf.carona.api.entity.customer.Customer;

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
@Table(name = "tab_rides_reservations")
public class RideReservation extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ride_program_id", nullable = false)
    private RideProgram rideProgram;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Override
    public String toString() {
        return "RideReservation{" +
                "rideProgram=" + rideProgram +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RideReservation rideReservation)) return false;

        return getId() != null && Objects.equals(getId(), rideReservation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}