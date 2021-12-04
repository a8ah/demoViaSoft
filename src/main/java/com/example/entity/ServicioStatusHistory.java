package com.example.entity;

import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import com.example.arch.SCHEMAS;

@Entity
@Table(schema = SCHEMAS.base)
public class ServicioStatusHistory extends BaseEntity{
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;    

    // Service state (up, Pending,Down)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Status status;
    
    // Estado o Provinvia
    @ManyToOne(optional = false)
    private State state;

    // Servicio
    @ManyToOne(optional = false)
    private Servicio service;

    public ServicioStatusHistory(){}

    // public ServiceStatusHistory(){}
    
    @Override
    public UUID getId() {        
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
        
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Servicio getService() {
        return this.service;
    }

    public void setService(Servicio service) {
        this.service = service;
    }

}
