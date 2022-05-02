package com.iesb.gab.raf.carona.api.payload.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public final class UserReinitializePasswordRequest {

    @Pattern(
            regexp = "^.*(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$&*+=%]).{8,25}.*$",
            message = "The password must contain at least one lower case, one upper case, one number and one special " +
                    "character",
            flags = Pattern.Flag.UNICODE_CASE
    )
    private String newPassword;
}
