package com.iesb.gab.raf.carona.api.dto.user;

import com.iesb.gab.raf.carona.api.entity.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends UserBaseDto {

    public UserDto(final User user) {
        super(user);
    }
}
