package com.stratum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stratum.model.ProjectParticipant;

@Repository
public interface ProjectParticipantRepository extends JpaRepository<ProjectParticipant, Long>{

}
