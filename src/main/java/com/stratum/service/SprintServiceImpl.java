package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.Sprint;
import com.stratum.repository.SprintRepository;

@Service
public class SprintServiceImpl implements SprintService{

	@Autowired
	SprintRepository sprintRepository;
	
	@Override
	public List<Sprint> list() {
		return sprintRepository.findAll();
	}

	@Override
	public Optional<Sprint> getOne(Long id) {
		return sprintRepository.findById(id);
	}

	@Override
	public void save(Sprint object) {
		sprintRepository.save(object);
		
	}

	@Override
	public void delete(Sprint object) {
		sprintRepository.delete(object);
		
	}

	@Override
	public boolean exists(Long id) {
		return sprintRepository.findById(id).isPresent();
	}

	@Override
	public List<Sprint> getAllForProject(Long id) {
		return sprintRepository.getAllForProject(id);
	}

}
