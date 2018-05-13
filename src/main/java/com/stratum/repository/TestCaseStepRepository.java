package com.stratum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stratum.model.Functionality;
import com.stratum.model.TestCaseStep;

public interface TestCaseStepRepository extends JpaRepository<TestCaseStep, Long>{

	@Query(value = "select * from test_case_step where usecase = ?1", nativeQuery = true)
	public List<Functionality> getAllForUsecase(Long id);
	
}
