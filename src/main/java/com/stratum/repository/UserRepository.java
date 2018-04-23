package com.stratum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stratum.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
