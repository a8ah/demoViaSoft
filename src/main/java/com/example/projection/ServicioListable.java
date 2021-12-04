package com.example.projection;

import javax.persistence.Column;
import java.util.UUID;

public class ServicioListable {

    private UUID id;

    @Column(nullable = false)
    private String nombre;

    
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }    


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ServicioListable(UUID id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


}
