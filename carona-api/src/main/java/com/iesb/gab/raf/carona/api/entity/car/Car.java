package com.iesb.gab.raf.carona.api.entity.car;

import com.iesb.gab.raf.carona.api.entity.AbstractBaseEntity;
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
@Table(name = "tab_cars")
public class Car extends AbstractBaseEntity {

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "license_plate", nullable = false, length = 20)
    private String licensePlate;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "nb_seats", nullable = false)
    private Integer nbSeats;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "driver_id", nullable = false)
    private Customer driver;

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", color='" + color + '\'' +
                ", nbSeats=" + nbSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;

        return getId() != null && Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}