package com.iesb.gab.raf.carona.api.payload.response.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenBaseResponse {

    protected String type = "Bearer";
    protected String accessToken;
    protected String refreshToken;

    public JwtTokenBaseResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
