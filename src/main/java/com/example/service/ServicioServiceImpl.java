package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.entity.Servicio;
import com.example.repo.IServicioRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class ServicioServiceImpl implements IServicioService{

  protected final Log logger = LogFactory.getLog(this.getClass());

@Autowired
  IServicioRepository mRepository;

@Override
public List<Servicio> getALl() {
  return mRepository.findAll();
}

@Override
public Optional<Servicio> findById(UUID id) {
  // TODO Auto-generated method stub
  return null;
}

@Override
@Transactional
public boolean crear(Servicio service) {

  if (service.getId() == null) {
    service.setEliminado(false);
    service.setCreado(LocalDateTime.now());
    } else {
      service.setModificado(LocalDateTime.now());
    }    
  mRepository.save(service);
  return true;
}

@Override
public String delete(UUID id) {
  // TODO Auto-generated method stub
  return null;
}

@Override
public String update(UUID id) {
  // TODO Auto-generated method stub
  return null;
}






}