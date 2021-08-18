package com.authorizationMicroservice.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorizationMicroservice.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("Start loadUserByUsername");

		com.authorizationMicroservice.model.User user = loginRepository.findByUsername(username);

		if (user == null) {

			log.debug("User not found:" + username);
			throw new UsernameNotFoundException("User not found !!");
		}
		log.debug("User found: " + user.getUsername());
		
		log.info("end loadUserByUsername");

		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
