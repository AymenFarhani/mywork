package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.models.UserRequest;
import com.example.demo.models.UserResponse;
import com.example.demo.service.IUserService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

	@Autowired
	private IUserService userService; // HAS-A

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer idUser = userService.save(user);
		String body = "User '" + idUser + "' saved";
		return ResponseEntity.ok(body);
	}

	@PostMapping(value="/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
		String token = jwtUtil.generateToken(userRequest.getUsername());
		return ResponseEntity.ok(new UserResponse(token, "Success!"));

	}
}
