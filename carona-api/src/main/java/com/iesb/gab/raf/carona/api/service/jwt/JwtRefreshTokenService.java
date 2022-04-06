package com.iesb.gab.raf.carona.api.service.jwt;

import com.iesb.gab.raf.carona.api.entity.user.RefreshToken;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.RefreshTokenException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;

public interface JwtRefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken getByToken(String token) throws ResourceNotFoundException;

    RefreshToken verifyExpiration(RefreshToken token) throws RefreshTokenException;
}
