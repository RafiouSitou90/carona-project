package com.iesb.gab.raf.carona.api.repository.user;

import com.iesb.gab.raf.carona.api.entity.user.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);
}