package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stratum.model.Functionality;
import com.stratum.repository.FunctionalityRepository;

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

}
