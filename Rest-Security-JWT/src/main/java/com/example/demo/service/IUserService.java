package com.example.demo.service;

import java.util.Optional;

import com.example.demo.models.User;

public interface IUserService {
	
	Integer save(User user);
	
	Optional<User> findUserByUsername(String username);

}
