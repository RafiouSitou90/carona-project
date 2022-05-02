package com.iesb.gab.raf.carona.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoginBadCredentialsException extends AuthenticationException {

    public LoginBadCredentialsException() {
        super("Username or password incorrect");
    }

    public LoginBadCredentialsException(String message) {
        super(message);
    }
}
