package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.entity.State;

import org.springframework.transaction.annotation.Transactional;

public interface IStateService {

    public List<State> getALl();

	public Optional<State> findById(UUID id);

	@Transactional
	public boolean crear(State state);

	@Transactional
	public String delete(UUID id);

	@Transactional
	public String update(UUID id);
}
