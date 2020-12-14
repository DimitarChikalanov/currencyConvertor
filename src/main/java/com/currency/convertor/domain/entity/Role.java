package com.currency.convertor.domain.entity;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Erole erole;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "roles_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;



    public Erole getErole() {
        return erole;
    }

    public void setErole(Erole erole) {
        this.erole = erole;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
