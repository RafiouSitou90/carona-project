package com.iesb.gab.raf.carona.api.event.user;

import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.event.EntityCreatedEvent;

public class UserPasswordUpgradedEvent extends EntityCreatedEvent<ResetPasswordToken> {

    public UserPasswordUpgradedEvent(ResetPasswordToken resetPasswordToken, Object source) {
        super(resetPasswordToken, source);
    }
}
