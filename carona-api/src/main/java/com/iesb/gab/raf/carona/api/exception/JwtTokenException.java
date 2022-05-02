package com.iesb.gab.raf.carona.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class JwtTokenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public JwtTokenException(String message) {
        super(message);
    }
}
