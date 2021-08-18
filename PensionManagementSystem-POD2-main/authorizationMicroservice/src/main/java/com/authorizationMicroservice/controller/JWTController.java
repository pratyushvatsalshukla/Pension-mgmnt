package com.authorizationMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authorizationMicroservice.model.JWTResponse;
import com.authorizationMicroservice.model.User;
import com.authorizationMicroservice.service.CustomUserDetailsService;
import com.authorizationMicroservice.util.JWTUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JWTController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	private UserDetails userDetails;
	
	/*
	 * POST: localhost:8084/authenticate
	 * 
	 * {
	 *     "username" : "admin",
	 *     "password" : "admin"
	 * }
	 */

	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody User authenticationRequest) throws Exception {

		log.info("Start generateToken");
		log.debug(authenticationRequest.getUsername() + " " + authenticationRequest.getPassword());
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			throw new Exception("Incorrect username or password", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		String token = jwtUtil.generateToken(userDetails);

		log.debug("JWT Token: " + token);
		
		log.info("end generateToken");

		return ResponseEntity.ok(new JWTResponse(token));
	}
	
	/*
	 * POST:  localhost:8084/validate
	 * 
	 * {
	 *    "token" : " "
	 * }
	 */

	@PostMapping("/validate")
	public Boolean validateToken(@RequestBody JWTResponse response) {
		log.info("start validateToken");
		String token=response.getToken();
		log.debug(token);
		log.info("End validateToken");
		return jwtUtil.validateToken(token, userDetails);
	}

}
