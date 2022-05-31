package com.iesb.gab.raf.carona.api.payload.request.car;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public final class CarRequest {

    @NotBlank(message = "The model cannot be blank")
    private String model;

    @NotBlank(message = "The license plate cannot be blank")
    private String licensePlate;

    @NotBlank(message = "The color cannot be blank")
    private String color;

    @Positive(message = "The number of seats must be greater than to zero")
    @Min(value = 2, message = "The number of seats minimum allowed is 2")
    private Integer nbSeats;
}
