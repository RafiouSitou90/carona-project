package com.iesb.gab.raf.carona.api.event;

import lombok.Getter;

import org.springframework.context.ApplicationEvent;

@Getter
public class EntityCreatedEvent<T> extends ApplicationEvent {

    private final T entity;

    public EntityCreatedEvent(T entity, Object source) {
        super(source);
        this.entity = entity;
    }
}
