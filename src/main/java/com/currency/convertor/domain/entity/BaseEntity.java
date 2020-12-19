package com.currency.convertor.domain.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BaseEntity() { }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //Column(name = "id"
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
