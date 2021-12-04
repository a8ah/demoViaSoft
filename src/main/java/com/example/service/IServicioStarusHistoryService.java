package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.example.entity.Servicio;
import com.example.entity.ServicioStatusHistory;
import com.example.projection.ServicioListable;

import org.springframework.transaction.annotation.Transactional;

public interface IServicioStarusHistoryService {

    public List<ServicioStatusHistory> getALl();

	public Optional<ServicioStatusHistory> findById(UUID id);

	@Transactional
	public boolean crear(ServicioStatusHistory service);

	public String delete(UUID id);

	public String update(UUID id);
}
