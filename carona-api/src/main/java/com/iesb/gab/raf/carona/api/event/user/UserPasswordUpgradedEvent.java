package com.iesb.gab.raf.carona.api.event.user;

import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.event.EntityCreatedEvent;
import lombok.Getter;

@Getter
public class UserPasswordUpgradedEvent extends EntityCreatedEvent<ResetPasswordToken> {

    private final String loginUrl;

    public UserPasswordUpgradedEvent(ResetPasswordToken resetPasswordToken, String loginUrl, Object source) {
        super(resetPasswordToken, source);
        this.loginUrl = loginUrl;
    }
}
