package com.currency.convertor.domain.entity;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Erole erole;

    public Erole getErole() {
        return erole;
    }

    public void setErole(Erole erole) {
        this.erole = erole;
    }

}
