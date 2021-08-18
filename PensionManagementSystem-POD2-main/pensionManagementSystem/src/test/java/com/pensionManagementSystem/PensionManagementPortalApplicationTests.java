package com.pensionManagementSystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pensionManagementSystem.model.Bank;
import com.pensionManagementSystem.model.JWTResponse;
import com.pensionManagementSystem.model.PensionDetail;
import com.pensionManagementSystem.model.PensionerDetail;
import com.pensionManagementSystem.model.PensionerInput;
import com.pensionManagementSystem.model.ProcessPensionInput;
import com.pensionManagementSystem.model.User;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
class PensionManagementPortalApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void main()
	{
		PensionManagementSystemApplication.main(new String[] {});
	}	
	
	@Test
	void testPensionerDetail() {
		EqualsVerifier.simple().forClass(PensionerDetail.class).verify();
	}
	
	@Test
	void testBank() {
		EqualsVerifier.simple().forClass(Bank.class).verify();
	}
	
	@Test
	void testPensionerInput() {
		EqualsVerifier.simple().forClass(PensionerInput.class).verify();
	}
	
	@Test
	void testJWTResponse() {
		EqualsVerifier.simple().forClass(JWTResponse.class).verify();
	}
	
	@Test
	void testProcessPensionerInput() {
		EqualsVerifier.simple().forClass(ProcessPensionInput.class).verify();
	}
	
	@Test
	void testPensionDetail() {
		EqualsVerifier.simple().forClass(PensionDetail.class).verify();
	}
	
	@Test
	public void testGetToken() {
		JWTResponse jwtResponse = new JWTResponse("abc");
		assertEquals(jwtResponse.getToken(), "abc");
	}
	
	@Test
	void testNoArgsBank()
	{
		assertThat(new Bank()).isNotNull();
	}
	
	@Test
	void testAllArgsBank()
	{
		Bank bank = new Bank("SBI", 11223344l, "public");
		assertThat(assertThat(bank).isNotNull());
	}
	
	@Test
	void testSetterBank()
	{
		Bank bank = new Bank();
		bank.setAccountNumber(22334455);
		bank.setBankName("SBI");
		bank.setBankType("public");
		assertThat(assertThat(bank).isNotNull());
	}
	
	@Test
	void testNoArgsJWTResponse()
	{
		assertThat(new JWTResponse()).isNotNull();
	}
	
	@Test
	void testSetterJWTResponse()
	{
		JWTResponse jwtResponse = new JWTResponse();
		jwtResponse.setToken("token");
	}
	
	@Test
	void testNoArgsPensionDetail()
	{
		assertThat(new PensionDetail()).isNotNull();
	}
	
	@Test
	void testAllArgsPensionDetail()
	{
		PensionDetail pensionDetail=new PensionDetail("Mounika",new Date(1999, 12, 10) , "PIYTH7890L", "family", 52131.0);
		assertThat(assertThat(pensionDetail).isNotNull());
	}
	
	@Test
	void testNoArgsPensionerDetail()
	{
		assertThat(new PensionerDetail()).isNotNull();
	}
	
	@Test
	void testAllArgsPensionerDetail()
	{
		Bank bank=new Bank("SBI", 77889966l, "public");
		PensionerDetail pensionerDetail=new PensionerDetail("Mounika", new Date(1999, 12, 10), "IUYTR9870L", 45000.0, 5236.0, "family", bank);
		assertThat(assertThat(pensionerDetail).isNotNull());
	}
	
	@Test
	void testSetterPensionDetail()
	{
		PensionDetail pd= new PensionDetail();
		pd.setDateOfBirth(new Date());
		pd.setName("Mounika");
		pd.setPan("GTYIK7412L");
		pd.setPensionAmount(29950.0);
		pd.setPensionType("family");
		assertThat(assertThat(pd).isNotNull());
	}
	
	@Test
	void testSetterPensionerDetail()
	{
		PensionerDetail pensionerDetail=new PensionerDetail();
		Bank bank=new Bank();
		bank.setAccountNumber(11223344);
		bank.setBankName("SBI");
		bank.setBankType("public");
		pensionerDetail.setDateOfBirth(new Date(1999, 12, 10));
		pensionerDetail.setName("Mounika");
		pensionerDetail.setPan("ITHYU1236L");
		pensionerDetail.setAllowance(45000.0);
		pensionerDetail.setBank(bank);
		pensionerDetail.setSalary(100000.0);
		pensionerDetail.setPensionType("family");
		assertThat(assertThat(pensionerDetail).isNotNull());
	}
	
	@Test
	void testSetterPensionerInput()
	{
		PensionerInput pensionerInput=new PensionerInput();
		pensionerInput.setAadharNumber(112233445566l);
		pensionerInput.setName("Mounika");
		pensionerInput.setDateOfBirth(new Date(1999, 12, 10));
		pensionerInput.setPan("ITHYU1236L");
		pensionerInput.setPensionType("family");
		assertThat(assertThat(pensionerInput).isNotNull());
	}
	
	@Test
	void testNoArgsPensionerInput()
	{
		assertThat(new PensionerInput()).isNotNull();
	}
	
	@Test
	void testAllArgsPensionerInput()
	{
		PensionerInput pensionerInput=new PensionerInput("Mounika", new Date(1999, 12, 10), "IHTYU0987L", 112233445566l, "family");
		assertThat(assertThat(pensionerInput).isNotNull());
	}
	
	@Test
	void testAllArgsProessPensionInput()
	{
		ProcessPensionInput ps = new ProcessPensionInput(112233445566l, 23955.0, 500);
		assertThat(assertThat(ps).isNotNull());
	}
	
	@Test
	void testNoArgsProcessPensionInput()
	{
		assertThat(new ProcessPensionInput()).isNotNull();
	}
	
	@Test
	void testSetterProcessPensionInputTest()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadharNumber(102233445566l);
		processPensionInput.setPensionAmount(45500.0);
		processPensionInput.setServiceCharge(500);
		assertThat(assertThat(processPensionInput).isNotNull());
	}
	
	@Test
	void testNoArgsUser()
	{
		assertThat(new User()).isNotNull();
	}
	
	@Test
	void testSetterUser()
	{
		User user=new User();
		user.setId(1);
		user.setPassword("admin");
		user.setUsername("admin");
		assertThat(assertThat(user).isNotNull());
	}
	

}
