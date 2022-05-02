package com.iesb.gab.raf.carona.api.entity.builder.user;

import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.entity.user.User;

import java.time.Instant;

public final class ResetPasswordTokenBuilder {

    private final ResetPasswordToken resetPasswordToken;

    private ResetPasswordTokenBuilder() {
        resetPasswordToken = new ResetPasswordToken();
    }

    public static ResetPasswordTokenBuilder builder() {
        return new ResetPasswordTokenBuilder();
    }

    public ResetPasswordTokenBuilder withUser(User user) {
        resetPasswordToken.setUser(user);

        return this;
    }

    public ResetPasswordTokenBuilder withToken(String token) {
        resetPasswordToken.setToken(token);

        return this;
    }

    public ResetPasswordTokenBuilder withExpiresAt(Instant expiresAt) {
        resetPasswordToken.setExpiresAt(expiresAt);

        return this;
    }

    public ResetPasswordToken build() {
        return resetPasswordToken;
    }
}
