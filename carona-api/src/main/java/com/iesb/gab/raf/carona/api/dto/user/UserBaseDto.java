package com.iesb.gab.raf.carona.api.dto.user;

import com.iesb.gab.raf.carona.api.dto.AbstractBaseDto;
import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.entity.user.User;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserBaseDto extends AbstractBaseDto implements Serializable {

    protected String username;
    protected String email;
    protected Set<String> roles;

    public UserBaseDto(final User user) {
        super(user);
        username = user.getUsername();
        email = user.getEmail();
        roles = getUserRolesToString(user.getRoles());
    }

    private Set<String> getUserRolesToString(Set<Role> roles) {
        Set<String> strRoles = new HashSet<>();
        roles.forEach(role -> strRoles.add(role.getName()));

        return strRoles;
    }
}
