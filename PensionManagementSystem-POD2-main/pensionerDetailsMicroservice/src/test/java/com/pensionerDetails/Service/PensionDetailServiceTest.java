package com.pensionerDetails.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pensionerDetailsMicroservice.Model.Bank;
import com.pensionerDetailsMicroservice.Model.PensionerDetail;
import com.pensionerDetailsMicroservice.Service.PensionerdetailService;
import com.pensionerDetailsMicroservice.Util.DateUtil;
@SpringBootTest(classes = PensionDetailServiceTest.class)
@AutoConfigureMockMvc
public class PensionDetailServiceTest {

	@InjectMocks
	private PensionerdetailService pds;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	

	@Test
	public void testNotNullPensionDetailServiceObject() {
		assertNotNull(pds);
	}

	@Test
	public void testCorrectDetailsReturnedFromServiceWithCorrectAadharNumber() throws NumberFormatException, IOException, AadharNumberNotFoundException, ParseException  {

			PensionerDetail pensionerDetail = new PensionerDetail("Padmini", DateUtil.parseDate("30-08-2000"), "PCASD1234Q",
					45000, 2000, "family", new Bank("SBI", 11223344, "private"));
			
			PensionerDetail pd=pds.getPensionerDetailByAadhaarNumber(102233445566L);
						assertEquals(pd, pensionerDetail);
	}

}
