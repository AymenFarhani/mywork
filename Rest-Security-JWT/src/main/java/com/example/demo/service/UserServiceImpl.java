package com.example.demo.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Integer save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user).getId();

	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	// ------------------------------------------------------------------------//
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = findUserByUsername(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("User not exist!");
		}

		User user = opt.get();
		return new org.springframework.security.core.userdetails.User(
				username,
				user.getPassword(),
				user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList()));
	}

}
