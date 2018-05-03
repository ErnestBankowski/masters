package com.stratum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.stratum.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
