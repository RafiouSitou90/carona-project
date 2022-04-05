package com.iesb.gab.raf.carona.api.entity.user;

import com.iesb.gab.raf.carona.api.entity.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_roles", indexes = {
        @Index(name = "role_name_uk", columnList = "name", unique = true)
})
public class Role extends AbstractBaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Override
    public String toString() {
        // @TODO Implement toString method
        return null;
    }

    @Override
    public boolean equals(Object o) {
        // @TODO Implement equals method
        return false;
    }

    @Override
    public int hashCode() {
        // @TODO Implement equals hashCode
        return 0;
    }
}