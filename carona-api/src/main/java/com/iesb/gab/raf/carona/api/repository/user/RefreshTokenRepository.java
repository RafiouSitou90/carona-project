package com.iesb.gab.raf.carona.api.repository.user;

import com.iesb.gab.raf.carona.api.entity.user.RefreshToken;
import com.iesb.gab.raf.carona.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByToken(String token);
}