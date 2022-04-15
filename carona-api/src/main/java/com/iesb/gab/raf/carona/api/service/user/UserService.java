package com.iesb.gab.raf.carona.api.service.user;

import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.auth.AuthSignUpRequest;
import com.iesb.gab.raf.carona.api.payload.request.user.UserReinitializePasswordRequest;

import java.util.Set;

public interface UserService {

    User save(AuthSignUpRequest authSignUpRequest, Set<Role> roles) throws ResourceAlreadyExistsException;

    void enableUser(Long id) throws ResourceNotFoundException;

    void upgradePassword(User user, UserReinitializePasswordRequest userReinitializePasswordRequest);
}
