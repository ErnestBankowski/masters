package com.stratum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stratum.model.Functionality;

public interface FunctionalityRepository extends JpaRepository<Functionality, Long>{

	@Query(value = "select * from functionality where sprint = ?1", nativeQuery = true)
	public List<Functionality> getAllForSprint(Long id);
	
}
