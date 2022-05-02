package com.iesb.gab.raf.carona.api.payload.response.jwt;

import com.iesb.gab.raf.carona.api.dto.user.UserDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JwtTokenResponse extends JwtTokenBaseResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final Set<String> roles;

    public JwtTokenResponse(UserDto user, String accessToken, String refreshToken) {
        super(accessToken, refreshToken);
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        roles = user.getRoles();
    }
}
