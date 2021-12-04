package com.example.entity;

public enum Status {
	ACTIVE("ACTIVE","A"),
    PENDING("PENDING","P"),
	INACTIVE("INACTIVE","I"),
    NOT_AVAILABLE("NOT_AVAILABLE","NA"),
    NOTPRESENT("NOTPRESENT","NP");

	private String nombre;
    private String abreviatura;

	Status(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return nombre;
    }
}