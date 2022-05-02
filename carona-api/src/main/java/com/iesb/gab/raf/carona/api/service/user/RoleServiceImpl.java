package com.iesb.gab.raf.carona.api.service.user;

import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.repository.user.RoleRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSaveByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);

        if (role.isEmpty()) {
            return roleRepository.save(new Role(name));
        } else {
            return role.get();
        }
    }
}
