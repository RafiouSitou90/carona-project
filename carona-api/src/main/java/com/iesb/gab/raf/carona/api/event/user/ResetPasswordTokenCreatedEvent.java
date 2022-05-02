package com.iesb.gab.raf.carona.api.event.user;

import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.event.EntityCreatedEvent;

import lombok.Getter;

@Getter
public class ResetPasswordTokenCreatedEvent extends EntityCreatedEvent<ResetPasswordToken> {

    private final String resetUrl;

    public ResetPasswordTokenCreatedEvent(ResetPasswordToken resetPasswordToken, String resetUrl, Object source) {
        super(resetPasswordToken, source);
        this.resetUrl = resetUrl;
    }
}
