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
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> maybeUser = userRepository.findById(id);
		if(maybeUser.isPresent()){
			return maybeUser.get();
		}else{
			throw new ResourceNotFoundException("user", "id", id);
		}
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);		
	}

}
