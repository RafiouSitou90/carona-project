package com.iesb.gab.raf.carona.api.service.jwt;

import com.iesb.gab.raf.carona.api.entity.user.RefreshToken;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.RefreshTokenException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.repository.user.RefreshTokenRepository;
import com.iesb.gab.raf.carona.api.util.jwt.JwtUtils;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {

    private final static String RESOURCE_NAME = "Refresh Token";

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtils jwtUtils;

//    @Value("${rafdev.app.jwt.jwtRefreshExpirationMs}")
//    private final long jwtRefreshExpirationMs = 604800000;

    @Override
    public RefreshToken createRefreshToken(User user) {
        final long jwtRefreshExpirationMs = 604800000;
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUser(user);

        if (refreshToken.isPresent()) {
            RefreshToken refreshTokenFound = refreshToken.get();
            refreshTokenFound.setToken(jwtUtils.generateRefreshToken(user));
            refreshTokenFound.setExpiresAt(Instant.now().plusMillis(jwtRefreshExpirationMs));

            return refreshTokenRepository.save(refreshTokenFound);
        }

        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setUser(user);
        newRefreshToken.setToken(jwtUtils.generateRefreshToken(user));
        newRefreshToken.setExpiresAt(Instant.now().plusMillis(jwtRefreshExpirationMs));

        return refreshTokenRepository.save(newRefreshToken);
    }

    @Override
    public RefreshToken getByToken(String token) throws ResourceNotFoundException {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "Token", token));
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) throws RefreshTokenException {

        if (refreshToken.getExpiresAt().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException(
                    refreshToken.getToken(),
                    "Refresh refresh was expired. Please make a new sign-in request"
            );
        }

        return refreshToken;
    }
}
