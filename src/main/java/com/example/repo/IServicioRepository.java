package com.example.repo;

import com.example.entity.Servicio;

import org.springframework.stereotype.Repository;

@Repository
public interface IServicioRepository extends  EntityRepository<Servicio> {
}