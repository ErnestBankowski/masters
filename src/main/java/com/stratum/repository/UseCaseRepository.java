package com.stratum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stratum.model.Functionality;
import com.stratum.model.UseCase;

public interface UseCaseRepository extends JpaRepository<UseCase, Long>{

	@Query(value = "select * from use_case where functionality = ?1", nativeQuery = true)
	public List<UseCase> getForFunctionality(Long id);
	
}
