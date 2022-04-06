package com.iesb.gab.raf.carona.api.service.user;

import com.iesb.gab.raf.carona.api.entity.builder.user.UserBuilder;
import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.auth.AuthSignUpRequest;
import com.iesb.gab.raf.carona.api.repository.user.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final static String RESOURCE_NAME = "User";

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(AuthSignUpRequest authSignUpRequest, Set<Role> roles) throws ResourceAlreadyExistsException {

        if (userRepository.existsByUsernameIgnoreCase(authSignUpRequest.getUsername())) {
            throw new ResourceAlreadyExistsException(RESOURCE_NAME, "Username", authSignUpRequest.getUsername());
        }

        if (userRepository.existsByEmailIgnoreCase(authSignUpRequest.getEmail())) {
            throw new ResourceAlreadyExistsException(RESOURCE_NAME, "Email", authSignUpRequest.getEmail());
        }

        User user = UserBuilder.builder()
                .withEmail(authSignUpRequest.getEmail())
                .withUsername(authSignUpRequest.getUsername())
                .withPassword(getPasswordHashed(authSignUpRequest.getPassword()))
                .withRoles(roles)
                .withIsEnabled(false)
                .build();

        return userRepository.save(user);
    }

    @Override
    public void enableUser(Long id) throws ResourceNotFoundException {
        User user = getUserOrThrowException(id);
        user.setIsEnabled(true);

        userRepository.save(user);
    }

    private String getPasswordHashed(String password) {
        return passwordEncoder.encode(password);
    }

    private User getUserOrThrowException(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(RESOURCE_NAME, "Id", id));
    }
}
