package com.iesb.gab.raf.carona.api.controller.auth;

import com.iesb.gab.raf.carona.api.exception.LoginBadCredentialsException;
import com.iesb.gab.raf.carona.api.payload.request.jwt.JwtRefreshTokenRequest;
import com.iesb.gab.raf.carona.api.payload.request.jwt.JwtSignInRequest;
import com.iesb.gab.raf.carona.api.payload.response.jwt.JwtRefreshTokenResponse;
import com.iesb.gab.raf.carona.api.payload.response.jwt.JwtTokenResponse;
import com.iesb.gab.raf.carona.api.service.jwt.JwtAuthService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class JwtAuthController {

    private final JwtAuthService jwtAuthService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(@RequestBody @Valid JwtSignInRequest jwtSignInRequest)
            throws LoginBadCredentialsException {
        return new ResponseEntity<>(jwtAuthService.signIn(jwtSignInRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtRefreshTokenResponse> refreshToken(
            @Valid @RequestBody JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        return new ResponseEntity<>(jwtAuthService.refreshToken(jwtRefreshTokenRequest), HttpStatus.OK);
    }
}
