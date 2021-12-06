package com.example.repo;

import com.example.entity.ServicioStatusHistory;

import org.springframework.stereotype.Repository;

@Repository
public interface IServicioStatusHistoryRepository extends  EntityRepository<ServicioStatusHistory> {
}