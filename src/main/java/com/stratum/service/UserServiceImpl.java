package com.stratum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.stratum.exception.ResourceNotFoundException;
import com.stratum.model.User;
import com.stratum.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> list() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getOne(String email) {
		Optional<User> maybeUser = userRepository.findById(email);
		return maybeUser;
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);		
	}

	@Override
	public boolean exists(String email) {
		return userRepository.findById(email).isPresent();
	}

}
