package com.iesb.gab.raf.carona.api.service.jwt;

import com.iesb.gab.raf.carona.api.dto.user.UserDto;
import com.iesb.gab.raf.carona.api.entity.user.RefreshToken;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.LoginBadCredentialsException;
import com.iesb.gab.raf.carona.api.exception.UserAccountDeactivatedException;
import com.iesb.gab.raf.carona.api.payload.request.jwt.JwtRefreshTokenRequest;
import com.iesb.gab.raf.carona.api.payload.request.jwt.JwtSignInRequest;
import com.iesb.gab.raf.carona.api.payload.response.jwt.JwtRefreshTokenResponse;
import com.iesb.gab.raf.carona.api.payload.response.jwt.JwtTokenResponse;
import com.iesb.gab.raf.carona.api.util.jwt.JwtUtils;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtAuthServiceImpl implements JwtAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtRefreshTokenService jwtRefreshTokenService;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @Override
    public JwtTokenResponse signIn(JwtSignInRequest jwtSignInRequest) throws LoginBadCredentialsException {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtSignInRequest.getUsername(),
                            jwtSignInRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException exception) {
            if (exception instanceof BadCredentialsException) {
                throw new LoginBadCredentialsException();
            } else if (exception instanceof DisabledException) {
                throw new UserAccountDeactivatedException();
            }
        }

        final User user = (User) userDetailsService.loadUserByUsername(jwtSignInRequest.getUsername());
        final String accessToken = jwtUtils.generateToken(user);

        final RefreshToken refreshToken = jwtRefreshTokenService.createRefreshToken(user);

        return new JwtTokenResponse(new UserDto(user), accessToken, refreshToken.getToken());
    }

    @Override
    public JwtRefreshTokenResponse refreshToken(JwtRefreshTokenRequest jwtRefreshTokenRequest) {

        RefreshToken refreshToken = jwtRefreshTokenService.getByToken(jwtRefreshTokenRequest.getRefreshToken());
        refreshToken = jwtRefreshTokenService.verifyExpiration(refreshToken);

        final User user = refreshToken.getUser();
        final String accessToken = jwtUtils.generateToken(user);

        return new JwtRefreshTokenResponse(accessToken, refreshToken.getToken());
    }
}
