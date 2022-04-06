package com.iesb.gab.raf.carona.api.payload.response.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRefreshTokenResponse extends JwtTokenBaseResponse {

    public JwtRefreshTokenResponse(String accessToken, String refreshToken) {
        super(accessToken, refreshToken);
    }
}
