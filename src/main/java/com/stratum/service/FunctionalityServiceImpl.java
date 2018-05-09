package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.Functionality;
import com.stratum.repository.FunctionalityRepository;

@Service
public class FunctionalityServiceImpl implements FunctionalityService{

	@Autowired
	FunctionalityRepository functionalityRepository;
	
	@Override
	public List<Functionality> list() {
		return functionalityRepository.findAll();
	}

	@Override
	public Optional<Functionality> getOne(Long id) {
		return functionalityRepository.findById(id);
	}

	@Override
	public void save(Functionality object) {
		functionalityRepository.save(object);
	}

	@Override
	public void delete(Functionality object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Functionality> getAllForSprint(long id) {
		return functionalityRepository.getAllForSprint(id);
	}

}
