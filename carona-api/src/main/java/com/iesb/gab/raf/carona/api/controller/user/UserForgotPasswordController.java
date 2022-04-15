package com.iesb.gab.raf.carona.api.controller.user;

import com.iesb.gab.raf.carona.api.dto.user.ResetPasswordTokenDto;
import com.iesb.gab.raf.carona.api.payload.request.user.UserPasswordResetRequest;
import com.iesb.gab.raf.carona.api.payload.request.user.UserReinitializePasswordRequest;
import com.iesb.gab.raf.carona.api.service.user.UserForgotPasswordService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserForgotPasswordController {

    private final UserForgotPasswordService userForgotPasswordService;

    @PostMapping("/forgot-password")
    public ResponseEntity<ResetPasswordTokenDto> processForgotPassword(@RequestBody @Valid UserPasswordResetRequest userPasswordResetRequest) {
        return  new ResponseEntity<>(userForgotPasswordService.processForgotPassword(userPasswordResetRequest), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody @Valid UserReinitializePasswordRequest userReinitializePasswordRequest,
            @RequestParam String token) {
        userForgotPasswordService.resetPassword(userReinitializePasswordRequest, token);

        return  new ResponseEntity<>("User Password successfully reinitialized", HttpStatus.OK);
    }
}
