package com.stratum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stratum.model.Project;
import com.stratum.model.ProjectParticipant;

@Repository
public interface ProjectParticipantRepository extends JpaRepository<ProjectParticipant, Long>{

	@Query(value = "select * from project_participant where project = ?1", nativeQuery = true)
	public List<ProjectParticipant> getAllForProject(Long id);
}
