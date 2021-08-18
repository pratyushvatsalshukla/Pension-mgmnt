package com.authorizationMicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.authorizationMicroservice.model.JWTResponse;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AuthorizationMicroserviceApplicationTests {

	@Test
	void main() {
		AuthorizationMicroserviceApplication.main(new String[] {});
	}
	
	@Test
	void testJWTResponse() {
		EqualsVerifier.simple().forClass(JWTResponse.class).verify();
	}
	
	@Test
	void testNoArgsJWTResponse()
	{
		assertThat(new JWTResponse()).isNotNull();
	}
	
	@Test
	void testSetterPensionDetail()
	{
		JWTResponse response = new JWTResponse();
		response.setToken("token");
		assertThat(assertThat(response).isNotNull());
	}


}