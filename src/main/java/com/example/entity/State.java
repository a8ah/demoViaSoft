package com.example.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import com.example.arch.SCHEMAS;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(schema = SCHEMAS.base)
public class State extends BaseEntity{
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Transient
    private List<Servicio> services;
    
    // @Transient
    // private List<ServiceStatusHistory> serviceStatus;

    // @Column(nullable = false)
    // @Enumerated(EnumType.STRING)
    // protected Status actualStatus;

    // @ManyToMany
    // @JoinTable(schema = SCHEMAS.base)
    // private List<Service> services;

    public State(){}

    @Override
    public UUID getId() {        
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
        
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
