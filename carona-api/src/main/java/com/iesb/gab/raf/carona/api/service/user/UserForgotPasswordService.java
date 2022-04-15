package com.iesb.gab.raf.carona.api.service.user;

import com.iesb.gab.raf.carona.api.dto.user.ResetPasswordTokenDto;
import com.iesb.gab.raf.carona.api.entity.user.ResetPasswordToken;
import com.iesb.gab.raf.carona.api.exception.ResourceBadRequestException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.user.UserPasswordResetRequest;
import com.iesb.gab.raf.carona.api.payload.request.user.UserReinitializePasswordRequest;

public interface UserForgotPasswordService {

    int EXPIRATION_SECONDS = 60 * 60 * 12; // 12 hours

    ResetPasswordToken save(ResetPasswordToken resetPasswordToken);

    ResetPasswordTokenDto processForgotPassword(UserPasswordResetRequest userPasswordResetRequest) throws ResourceNotFoundException;

    void resetPassword(UserReinitializePasswordRequest userReinitializePasswordRequest, String token) throws ResourceNotFoundException, ResourceBadRequestException;
}
