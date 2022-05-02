package com.iesb.gab.raf.carona.api.service.jwt;

import com.iesb.gab.raf.carona.api.entity.user.ConfirmationToken;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.repository.user.ConfirmationTokenRepository;
import com.iesb.gab.raf.carona.api.service.user.UserService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final static String RESOURCE_NAME = "Confirmation Token";

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private UserService userService;

    @Override
    public ConfirmationToken save(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken getByToken(String token) {
        return getConfirmationTokenOrThrowException(token);
    }

    @Override
    public ConfirmationToken setConfirmedAt(String token) {
        ConfirmationToken confirmationToken = getConfirmationTokenOrThrowException(token);
        confirmationToken.setConfirmedAt(Instant.now());
        userService.enableUser(confirmationToken.getUser().getId());

        return confirmationTokenRepository.save(confirmationToken);
    }

    private ConfirmationToken getConfirmationTokenOrThrowException(String token) {
        return confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "Token", token));
    }
}
