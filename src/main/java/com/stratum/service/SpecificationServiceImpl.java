package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.Specification;
import com.stratum.model.UseCase;
import com.stratum.repository.SpecificationRepository;

@Service
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
		specificationRepository.delete(object);
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Specification getForFunctionality(Long id) {
		List<Specification> forFunctionality = specificationRepository.getForFunctionality(id);
		if(forFunctionality.isEmpty()){
			return null;
		} else {
			return forFunctionality.get(0);
		}
	}

}
