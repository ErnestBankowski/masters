package com.stratum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stratum.model.Specification;
import com.stratum.model.UseCase;

public interface SpecificationRepository extends JpaRepository<Specification, Long>{

	@Query(value = "select * from specification where functionality = ?1", nativeQuery = true)
	public List<Specification> getForFunctionality(Long id);
	
}
