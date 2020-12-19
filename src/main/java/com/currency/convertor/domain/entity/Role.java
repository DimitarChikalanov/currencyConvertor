package com.currency.convertor.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ERole name;

    public ERole getName() {
        return name;
    }

    public void setName(ERole erole) {
        this.name = erole;
    }

}
