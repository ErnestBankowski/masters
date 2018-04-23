package com.stratum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stratum.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
