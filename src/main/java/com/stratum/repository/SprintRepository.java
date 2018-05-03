package com.stratum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stratum.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

	@Query(value = "select * from sprint where project = ?1", nativeQuery = true)
	public List<Sprint> getAllForProject(Long id);
}
