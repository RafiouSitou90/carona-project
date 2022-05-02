package com.iesb.gab.raf.carona.api.dto;

import com.iesb.gab.raf.carona.api.entity.AbstractBaseEntity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class AbstractBaseDto {

    protected Long id;
    protected Instant createdAt;
    protected Instant updatedAt;

    public AbstractBaseDto(final AbstractBaseEntity entity) {
        id = entity.getId();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();
    }
}
