package com.iesb.gab.raf.carona.api.repository.user;

import com.iesb.gab.raf.carona.api.entity.user.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}