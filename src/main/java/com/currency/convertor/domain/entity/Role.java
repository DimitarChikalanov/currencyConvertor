package com.currency.convertor.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private String roles;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "roles_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;
}
