package com.iesb.gab.raf.carona.api.dto.user;

import com.iesb.gab.raf.carona.api.dto.AbstractBaseDto;
import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ResetPasswordTokenDto extends AbstractBaseDto {

    private final String resetToken;
    private final Instant expiresAt;

    public ResetPasswordTokenDto(final ResetPasswordToken resetPasswordToken) {
        super(resetPasswordToken);
        resetToken = resetPasswordToken.getToken();
        expiresAt = resetPasswordToken.getExpiresAt();
    }
}
