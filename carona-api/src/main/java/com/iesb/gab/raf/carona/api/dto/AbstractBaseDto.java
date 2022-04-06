package com.iesb.gab.raf.carona.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class AbstractBaseDto {

    protected Long id;
    protected Instant createdAt;
    protected Instant updatedAt;
}
