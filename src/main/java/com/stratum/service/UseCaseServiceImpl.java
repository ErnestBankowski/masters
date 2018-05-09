package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.UseCase;
import com.stratum.repository.UseCaseRepository;

@Service
public class UseCaseServiceImpl implements UseCaseService{

	@Autowired
	UseCaseRepository useCaseRepository;
	
	@Override
	public List<UseCase> list() {
		return useCaseRepository.findAll();
	}

	@Override
	public Optional<UseCase> getOne(Long id) {
		return useCaseRepository.findById(id);
	}

	@Override
	public void save(UseCase object) {
		useCaseRepository.save(object);
		
	}

	@Override
	public void delete(UseCase object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
