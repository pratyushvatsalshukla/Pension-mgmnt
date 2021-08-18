package com.authorizationMicroservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.authorizationMicroservice.repository.LoginRepository;
import com.authorizationMicroservice.util.JWTUtil;

import io.jsonwebtoken.Claims;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = JWTUtilityClassTest.class)
public class JWTUtilityClassTest {

	@Mock
	LoginRepository repository;

	@InjectMocks
	JWTUtil util;
	
	@MockBean
	Claims claim;

	User userDetails=null;

	@Test
	void testGenerateTokenWithRandomUserGeneratesNotNull() {
		UserDetails details = new org.springframework.security.core.userdetails.User("admin", "admin",
				new ArrayList<>());
		
		assertThat(util.generateToken(details)).isNotNull();
	}
	
	@Test
	void validateTokenTest() {
		userDetails = new User("admin", "admin", new ArrayList<>());
		String generateToken = util.generateToken(userDetails);
		Boolean validateToken = util.validateToken(generateToken, userDetails);
		assertThat(validateToken).isTrue();
	}

}
