package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.entity.State;
import com.example.repo.IStateRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class StateServiceImpl implements IStateService{

  protected final Log logger = LogFactory.getLog(this.getClass());

@Autowired
static
  IStateRepository mRepository;

@Override
public List<State> getALl() {
  return mRepository.findAll();
}

@Override
public Optional<State> findById(UUID id) {
  // TODO Auto-generated method stub
  return null;
}

@Override
@Transactional
public boolean crear(State state) {

  if (state.getId() == null) {
    state.setEliminado(false);
    state.setCreado(LocalDateTime.now());
    } else {
      state.setModificado(LocalDateTime.now());
    }    
  mRepository.save(state);
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