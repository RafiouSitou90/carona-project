package com.iesb.gab.raf.carona.api.payload.request.ride;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public final class RideProgramRequest {

    @PositiveOrZero(message = "The city from id must be greater than or equal to zero")
    private Long cityFromId;

    @PositiveOrZero(message = "The city to id must be greater than or equal to zero")
    private Long cityToId;

    @NotBlank(message = "The day cannot be blank")
    private String day;

    @NotBlank(message = "The departure time cannot be blank")
    private String departureTime;

    @DecimalMin(value = "0.01")
    private BigDecimal price;
}
