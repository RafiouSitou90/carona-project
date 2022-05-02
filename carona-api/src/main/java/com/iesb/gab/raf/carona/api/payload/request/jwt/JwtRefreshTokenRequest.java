package com.iesb.gab.raf.carona.api.payload.request.jwt;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public final class JwtRefreshTokenRequest {

    @NotBlank(message = "The refresh token cannot be blank")
    private String refreshToken;
}
