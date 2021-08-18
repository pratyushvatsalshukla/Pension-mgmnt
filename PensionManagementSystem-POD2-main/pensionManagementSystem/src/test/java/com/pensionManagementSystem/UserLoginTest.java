package com.pensionManagementSystem;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionManagementSystem.model.User;

@SpringBootTest(classes = UserLoginTest.class)
public class UserLoginTest {
	User user = new User(1,"admin","admin");

	@Test
	public void testNotNullJwtRequest() {
		assertNotNull(user);
	}
	
	@Test
	public void testUsername() {
		user.setUsername("admin");
		assertEquals(user.getUsername(), "admin");
	}

	@Test
	public void testPassword() {
		user.setPassword("admin");
		assertEquals(user.getPassword(), "admin");
	}

}
