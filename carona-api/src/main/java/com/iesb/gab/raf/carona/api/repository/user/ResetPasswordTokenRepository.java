package com.iesb.gab.raf.carona.api.repository.user;

import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.entity.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {

    Optional<ResetPasswordToken> findByUser(User user);

    Optional<ResetPasswordToken> findByToken(String token);
}