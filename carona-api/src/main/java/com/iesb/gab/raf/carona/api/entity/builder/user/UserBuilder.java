package com.iesb.gab.raf.carona.api.entity.builder.user;

import com.iesb.gab.raf.carona.api.entity.user.ConfirmationToken;
import com.iesb.gab.raf.carona.api.entity.user.RefreshToken;
import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.entity.user.User;

import java.util.Set;

public final class UserBuilder {

    private final User user;

    private UserBuilder() {
        user = new User();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder withUsername(final String username) {
        user.setUsername(username);

        return this;
    }

    public UserBuilder withEmail(final String email) {
        user.setEmail(email);

        return this;
    }

    public UserBuilder withPassword(final String password) {
        user.setPassword(password);

        return this;
    }

    public UserBuilder withIsEnabled(final Boolean isEnabled) {
        user.setIsEnabled(isEnabled);

        return this;
    }

    public UserBuilder withIsNonExpired(final Boolean isNonExpired) {
        user.setIsNonExpired(isNonExpired);

        return this;
    }

    public UserBuilder withIsNonLocked(final Boolean isNonLocked) {
        user.setIsNonLocked(isNonLocked);

        return this;
    }

    public UserBuilder withIsCredentialsNonExpired(final Boolean isCredentialsNonExpired) {
        user.setIsCredentialsNonExpired(isCredentialsNonExpired);

        return this;
    }

    public UserBuilder withRoles(final Set<Role> roles) {
        user.setRoles(roles);

        return this;
    }

    public UserBuilder withRoles(final Role role) {
        if (user.getRoles().contains(role)) {
            return this;
        }

        user.getRoles().add(role);

        return this;
    }

    public UserBuilder withConfirmationToken(final ConfirmationToken confirmationToken) {
        user.setConfirmationToken(confirmationToken);

        return this;
    }

    public UserBuilder withRefreshToken(final RefreshToken refreshToken) {
        user.setRefreshToken(refreshToken);

        return this;
    }

    public User build() {
        return user;
    }
}
