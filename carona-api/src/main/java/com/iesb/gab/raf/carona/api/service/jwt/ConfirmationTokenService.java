package com.iesb.gab.raf.carona.api.service.jwt;

import com.iesb.gab.raf.carona.api.entity.user.ConfirmationToken;

public interface ConfirmationTokenService {

    int EXPIRATION_SECONDS = 60 * 60 * 12; // 12 hours

    ConfirmationToken save(ConfirmationToken confirmationToken);

    ConfirmationToken getByToken(String token);

    ConfirmationToken setConfirmedAt(String token);
}
