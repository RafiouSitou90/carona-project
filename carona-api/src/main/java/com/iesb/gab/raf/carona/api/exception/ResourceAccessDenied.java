package com.iesb.gab.raf.carona.api.exception;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceAccessDenied extends RuntimeException {

    public ResourceAccessDenied(String message) {
        super(message);
    }
}
