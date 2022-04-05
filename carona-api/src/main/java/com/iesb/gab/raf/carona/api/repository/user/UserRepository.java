package com.iesb.gab.raf.carona.api.repository.user;

import com.iesb.gab.raf.carona.api.entity.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username = ?1")
    Optional<User> findByUsernameOrEmailIgnoreCase(String username);

    Boolean existsByEmailIgnoreCase(String email);

    Boolean existsByUsernameIgnoreCase(String username);

    Optional<User> findByUsername(String username);
}
