package com.pensionerDetailsMicroservice.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pensionerDetailsMicroservice.Model.Bank;
import com.pensionerDetailsMicroservice.Model.PensionerDetail;
import com.pensionerDetailsMicroservice.Service.PensionerdetailService;
import com.pensionerDetailsMicroservice.Util.DateUtil;
@SpringBootTest(classes = PensionDetailsControllerTest.class)
public class PensionDetailsControllerTest {

	@InjectMocks
	PensionerDetailsController controller;

	@Mock
	PensionerdetailService service;

	

	@Test
	public void testToGetCorrectPenionerDetailsFromController() throws Exception {
		PensionerDetail pensionerDetail = new PensionerDetail("Jahnavi", DateUtil.parseDate("30-08-2000"), "PCASD1234Q",
				45000, 2000, "family", new Bank("SBI", 11223344, "private"));
		when(service.getPensionerDetailByAadhaarNumber(102233445566L)).thenReturn(pensionerDetail);
		PensionerDetail actual = controller.getPensionerDetailByAadhaar(102233445566L);
		assertNotNull(actual);
		assertEquals(actual, pensionerDetail);

	}
	
	@Test
	public void testToGetCorrectPenionerDetailsFromService() throws Exception{
		
		
		PensionerDetail pensionerDetail = new PensionerDetail("Jahnavi", DateUtil.parseDate("30-08-2000"), "PCASD1234Q",
				45000, 2000, "family", new Bank("SBI", 11223344, "private"));
		when(service.getPensionerDetailByAadhaarNumber(102233445566L)).thenReturn(pensionerDetail);
		PensionerDetail actual = service.getPensionerDetailByAadhaarNumber(102233445566L);
		assertNotNull(actual);
		assertEquals(actual, pensionerDetail);

		
	}

	/*@Test
	public void testForAadharNumberNotInCsvFile() throws AadharNumberNotFoundException {

		PensionerDetail actual = controller.getPensionerDetailByAadhaar(12345678888L);
		assertNull(actual);
	}*/

}
