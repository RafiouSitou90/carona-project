package com.iesb.gab.raf.carona.api.service.jwt;

import com.iesb.gab.raf.carona.api.exception.LoginBadCredentialsException;
import com.iesb.gab.raf.carona.api.payload.request.jwt.JwtRefreshTokenRequest;
import com.iesb.gab.raf.carona.api.payload.request.jwt.JwtSignInRequest;
import com.iesb.gab.raf.carona.api.payload.response.jwt.JwtRefreshTokenResponse;
import com.iesb.gab.raf.carona.api.payload.response.jwt.JwtTokenResponse;

public interface JwtAuthService {

    JwtTokenResponse signIn(JwtSignInRequest jwtSignInRequest) throws LoginBadCredentialsException;

    JwtRefreshTokenResponse refreshToken(JwtRefreshTokenRequest jwtRefreshTokenRequest);
}
