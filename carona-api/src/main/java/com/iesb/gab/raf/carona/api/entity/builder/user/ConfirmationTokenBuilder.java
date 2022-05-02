package com.iesb.gab.raf.carona.api.entity.builder.user;

import com.iesb.gab.raf.carona.api.entity.user.ConfirmationToken;
import com.iesb.gab.raf.carona.api.entity.user.User;

import java.time.Instant;

public final class ConfirmationTokenBuilder {

    private final ConfirmationToken confirmationToken;

    private ConfirmationTokenBuilder() {
        confirmationToken = new ConfirmationToken();
    }

    public static ConfirmationTokenBuilder builder() {
        return new ConfirmationTokenBuilder();
    }

    public ConfirmationTokenBuilder withUser(final User user) {
        confirmationToken.setUser(user);

        return this;
    }

    public ConfirmationTokenBuilder withToken(final String token) {
        confirmationToken.setToken(token);

        return this;
    }

    public ConfirmationTokenBuilder withExpiresAt(final Instant expiresAt) {
        confirmationToken.setExpiresAt(expiresAt);

        return this;
    }

    public ConfirmationTokenBuilder withConfirmedAt(final Instant confirmedAt) {
        confirmationToken.setConfirmedAt(confirmedAt);

        return this;
    }

    public ConfirmationToken build() {
        return confirmationToken;
    }
}
