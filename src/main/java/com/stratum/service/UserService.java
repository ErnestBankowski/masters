package com.stratum.service;

import java.util.List;

import com.stratum.model.User;

public interface UserService {	
	
	public List<User> listUsers();
	public User getUserById(Long id);
	public void saveUser(User user);
	public void deleteUser(User user);

}
