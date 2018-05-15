package com.stratum.service;

import java.util.List;

import com.stratum.model.Functionality;
import com.stratum.model.TestCaseStep;

public interface TestCaseStepService extends DataService<TestCaseStep, Long>{

	public List<TestCaseStep> getAllForUsecase(Long id);
}
