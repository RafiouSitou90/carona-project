package com.iesb.gab.raf.carona.api.util;

import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.ResourceAccessDenied;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public final class Utils {

    public static User getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }

        return (User) authentication.getPrincipal();
    }

    public static void denyAccessUnlessGranted(final User user, final String message) {
        if (!canAccess(user, getUserAuthenticated())) {
            throw new ResourceAccessDenied(message);
        }
    }

    private static Boolean canAccess(final User user, final User userAuthenticated) {
        if (user.equals(userAuthenticated)) {
            return true;
        } else {
            for (Role role: userAuthenticated.getRoles()) {
                if (Objects.equals(role.getName(), "ROLE_DRIVER")) {
                    return true;
                }
            }
        }

        return false;
    }
}
