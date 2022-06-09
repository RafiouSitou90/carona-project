package com.iesb.gab.raf.carona.api.entity.ride;

import com.iesb.gab.raf.carona.api.entity.AbstractBaseEntity;
import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.city.City;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_rides_programs")
public class RideProgram extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_from", nullable = false)
    private City cityFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_to", nullable = false)
    private City cityTo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "day", nullable = false, length = 20)
    private String day;

    @Column(name = "departure_time", nullable = false, length = 5)
    private String departureTime;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_full")
    private Boolean isFull = false;

    @OneToMany(mappedBy = "rideProgram", fetch = FetchType.EAGER, targetEntity = RideReservation.class)
    private Set<RideReservation> ridesReservations = new HashSet<>();

    @Override
    public String toString() {
        return "RideProgram{" +
                "cityFrom=" + cityFrom +
                ", cityTo=" + cityTo +
                ", car=" + car +
                ", day='" + day + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", isActive=" + isActive +
                ", isFull=" + isFull +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RideProgram rideProgram)) return false;

        return getId() != null && Objects.equals(getId(), rideProgram.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}