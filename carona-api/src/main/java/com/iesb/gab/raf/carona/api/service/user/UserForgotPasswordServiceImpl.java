package com.iesb.gab.raf.carona.api.service.user;

import com.iesb.gab.raf.carona.api.dto.user.ResetPasswordTokenDto;
import com.iesb.gab.raf.carona.api.entity.builder.user.ResetPasswordTokenBuilder;
import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.event.user.ResetPasswordTokenCreatedEvent;
import com.iesb.gab.raf.carona.api.event.user.UserPasswordUpgradedEvent;
import com.iesb.gab.raf.carona.api.exception.ResourceBadRequestException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.user.UserPasswordResetRequest;
import com.iesb.gab.raf.carona.api.payload.request.user.UserReinitializePasswordRequest;
import com.iesb.gab.raf.carona.api.repository.user.ResetPasswordTokenRepository;
import com.iesb.gab.raf.carona.api.repository.user.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserForgotPasswordServiceImpl implements UserForgotPasswordService {

    private final static String RESOURCE_NAME = "Reset Password Token";

    private final UserRepository userRepository;
    private final UserService userService;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ResetPasswordToken save(ResetPasswordToken resetPasswordToken) {
        return resetPasswordTokenRepository.save(resetPasswordToken);
    }

    @Override
    public ResetPasswordTokenDto processForgotPassword(UserPasswordResetRequest userPasswordResetRequest, String resetUrl)
            throws ResourceNotFoundException {

        Optional<User> user = userRepository.findByEmailIgnoreCase(userPasswordResetRequest.getEmail());

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User", "Email", userPasswordResetRequest.getEmail());
        }

        User userFound = user.get();
        Optional<ResetPasswordToken> resetPasswordToken = resetPasswordTokenRepository.findByUser(userFound);

        String token = UUID.randomUUID().toString();
        Instant expiresAt = Instant.now().plusSeconds(EXPIRATION_SECONDS);

        if (resetPasswordToken.isEmpty()) {
            ResetPasswordToken newResetPasswordToken = ResetPasswordTokenBuilder.builder()
                    .withUser(userFound)
                    .withToken(token)
                    .withExpiresAt(expiresAt)
                    .build();
            newResetPasswordToken = save(newResetPasswordToken);
            publishResetPasswordCreatedEvent(newResetPasswordToken, resetUrl);

            return new ResetPasswordTokenDto(newResetPasswordToken);
        }

        ResetPasswordToken resetPasswordTokenFound = resetPasswordToken.get();
        resetPasswordTokenFound.setToken(token);
        resetPasswordTokenFound.setExpiresAt(expiresAt);
        resetPasswordTokenFound = save(resetPasswordTokenFound);
        publishResetPasswordCreatedEvent(resetPasswordTokenFound, resetUrl);

        return new ResetPasswordTokenDto(resetPasswordTokenFound);
    }

    @Override
    public void resetPassword(UserReinitializePasswordRequest userReinitializePasswordRequest, String token, String loginUrl)
            throws ResourceNotFoundException, ResourceBadRequestException {

        ResetPasswordToken resetPasswordTokenFound = validateToken(token);

        userService.upgradePassword(resetPasswordTokenFound.getUser(), userReinitializePasswordRequest);
        resetPasswordTokenFound.setToken(null);
        resetPasswordTokenRepository.save(resetPasswordTokenFound);
        publishUserPasswordUpgradedEvent(resetPasswordTokenFound, loginUrl);
    }

    @Override
    public void validateResetPasswordToken(String token) {
        validateToken(token);
    }

    private void publishResetPasswordCreatedEvent(final ResetPasswordToken resetPasswordToken, String resetUrl) {
        ResetPasswordTokenCreatedEvent resetPasswordTokenCreatedEvent =
                new ResetPasswordTokenCreatedEvent(resetPasswordToken, resetUrl, this);
        eventPublisher.publishEvent(resetPasswordTokenCreatedEvent);
    }

    private void publishUserPasswordUpgradedEvent(final ResetPasswordToken resetPasswordToken, String loginUrl) {
        UserPasswordUpgradedEvent userPasswordUpgradedEvent =
                new UserPasswordUpgradedEvent(resetPasswordToken, loginUrl, this);
        eventPublisher.publishEvent(userPasswordUpgradedEvent);
    }

    private ResetPasswordToken validateToken(String token) {

        Optional<ResetPasswordToken> resetPasswordToken = resetPasswordTokenRepository.findByToken(token);

        if (resetPasswordToken.isEmpty()) {
            throw new ResourceNotFoundException("User Reset Password Token Invalid.");
        }

        ResetPasswordToken resetPasswordTokenFound = resetPasswordToken.get();

        if (resetPasswordTokenFound.getExpiresAt().isBefore(Instant.now())) {
            throw new ResourceBadRequestException("User Reset Password Token Expired");
        }

        return resetPasswordTokenFound;
    }
}
