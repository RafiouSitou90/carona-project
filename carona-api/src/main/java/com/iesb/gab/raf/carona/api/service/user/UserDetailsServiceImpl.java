package com.iesb.gab.raf.carona.api.service.user;

import com.iesb.gab.raf.carona.api.exception.LoginBadCredentialsException;
import com.iesb.gab.raf.carona.api.repository.user.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws LoginBadCredentialsException {
        return userRepository.findByUsernameOrEmailIgnoreCase(username)
                .orElseThrow(LoginBadCredentialsException::new);
    }
}
