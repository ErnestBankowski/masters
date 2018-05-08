package com.stratum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stratum.model.UseCase;

public interface UseCaseRepository extends JpaRepository<UseCase, Long>{

}
