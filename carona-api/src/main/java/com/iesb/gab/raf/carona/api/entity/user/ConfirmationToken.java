package com.iesb.gab.raf.carona.api.entity.user;

import com.iesb.gab.raf.carona.api.entity.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_user_confirmation_tokens")
public class ConfirmationToken extends AbstractBaseEntity {

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token", nullable = false, length = 500)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "confirmed_at")
    private Instant confirmedAt;

    @Override
    public String toString() {
        // @TODO Implement toString method
        return null;
    }

    @Override
    public boolean equals(Object o) {
        // @TODO Implement equals method
        return false;
    }

    @Override
    public int hashCode() {
        // @TODO Implement equals hashCode
        return 0;
    }
}