package com.iesb.gab.raf.carona.api.service.user;

import com.iesb.gab.raf.carona.api.entity.user.Role;

public interface RoleService {

    Role getOrSaveByName(String name);
}
