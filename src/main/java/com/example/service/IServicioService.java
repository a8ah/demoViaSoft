package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.example.entity.Servicio;
import com.example.projection.ServicioListable;

import org.springframework.transaction.annotation.Transactional;

public interface IServicioService {

    public List<Servicio> getALl();

	public Optional<Servicio> findById(UUID id);

	@Transactional
	public boolean crear(Servicio service);

	public String delete(UUID id);

	public String update(UUID id);
}
