package com.iesb.gab.raf.carona.api.payload.request.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public final class AuthSignUpRequest {

    @NotBlank(message = "The username cannot be blank")
    @Size.List ({
            @Size(min = 6, message = "The username must be at least {min} characters"),
            @Size(max = 100, message = "The username must be less than {max} characters")
    })
    private String username;

    @NotBlank(message = "The email cannot be blank")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "The password cannot be blank")
    @Size.List ({
            @Size(min = 8, message = "The password must be at least {min} characters"),
            @Size(max = 50, message = "The password must be less than {max} characters")
    })
    @Pattern(
            regexp = "^.*(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&*+=%]).{8,25}.*$",
            message = "The password must contain at least one lower case, one upper case, one number and one special " +
                    "character",
            flags = Pattern.Flag.UNICODE_CASE
    )
    private String password;
}
