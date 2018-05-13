package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratum.model.Functionality;
import com.stratum.model.TestCaseStep;
import com.stratum.repository.TestCaseStepRepository;

@Service
public class TestCaseStepServiceImpl implements TestCaseStepService{
	
	@Autowired
	TestCaseStepRepository testCaseStepRepository;

	@Override
	public List<TestCaseStep> list() {
		return testCaseStepRepository.findAll();
	}

	@Override
	public Optional<TestCaseStep> getOne(Long id) {
		return testCaseStepRepository.findById(id);
	}

	@Override
	public void save(TestCaseStep object) {
		testCaseStepRepository.save(object);
		
	}

	@Override
	public void delete(TestCaseStep object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Functionality> getAllForUsecase(Long id) {
		return testCaseStepRepository.getAllForUsecase(id);
	}

}
