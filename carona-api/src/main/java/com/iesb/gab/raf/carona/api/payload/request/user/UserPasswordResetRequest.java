package com.iesb.gab.raf.carona.api.payload.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public final class UserPasswordResetRequest {

    @NotBlank(message = "The email cannot be blank")
    @Email(message = "Invalid email address")
    private String email;
}
