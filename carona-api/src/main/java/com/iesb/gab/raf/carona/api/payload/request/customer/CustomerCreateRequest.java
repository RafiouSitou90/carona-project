package com.iesb.gab.raf.carona.api.payload.request.customer;

import com.iesb.gab.raf.carona.api.payload.request.auth.AuthSignUpRequest;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public final class CustomerCreateRequest {

    @Valid
    private AuthSignUpRequest user;

    @NotNull(message = "The CPF cannot be null")
    @CPF(message = "Invalid CPF")
    private String cpf;

    @NotNull(message = "The firstName cannot be null")
    @Size.List ({
            @Size(min = 3, message = "The firstName must be at least {min} characters"),
            @Size(max = 100, message = "The firstName must be less than {max} characters")
    })
    private String firstName;

    @NotNull(message = "The lastName cannot be null")
    @Size.List ({
            @Size(min = 3, message = "The lastName must be at least {min} characters"),
            @Size(max = 100, message = "The lastName must be less than {max} characters")
    })
    private String lastName;

    @NotNull(message = "The phone number cannot be null")
    private String phoneNumber;
}
