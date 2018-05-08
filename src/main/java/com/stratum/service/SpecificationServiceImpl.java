package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stratum.model.Specification;
import com.stratum.repository.SpecificationRepository;

public class SpecificationServiceImpl implements SpecificationService{

	@Autowired
	SpecificationRepository specificationRepository;
	
	@Override
	public List<Specification> list() {
		return specificationRepository.findAll();
	}

	@Override
	public Optional<Specification> getOne(Long id) {
		return specificationRepository.findById(id);
	}

	@Override
	public void save(Specification object) {
		specificationRepository.save(object);
	}

	@Override
	public void delete(Specification object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
