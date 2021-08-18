package com.pensionManagementSystem.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pensionManagementSystem.client.AuthorizationMicroserviceClient;
import com.pensionManagementSystem.client.ProcessPensionMicroserviceClient;
import com.pensionManagementSystem.model.JWTResponse;
import com.pensionManagementSystem.model.PensionDetail;
import com.pensionManagementSystem.model.PensionerInput;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PensionerInputController {
	@Autowired
	ProcessPensionMicroserviceClient processPensionMicroserviceClient;

	@Autowired
	AuthorizationMicroserviceClient authorizationMicroserviceClient;

	boolean invalidInput = false;
	boolean invalidAdhar = false;
	

	@GetMapping("/processPensionerInput")
	public String showPensionerInputForm(@ModelAttribute PensionerInput pensionerInput, HttpSession session,
			ModelMap model) {
		
		log.info("start showPensionerInputForm");
		
		String token=(String) session.getAttribute("token");
		JWTResponse response= new JWTResponse(token);

		boolean validated = authorizationMicroserviceClient.validateToken(response);
		if (validated) {
			if (invalidInput || invalidAdhar) {
				model.addAttribute("status", "Wrong Pension Data!!");
				
				invalidInput = false;
				invalidAdhar=false;
			}
			log.debug(""+pensionerInput);
			return "pensionerInput";
		}
		log.info("end showPensionerInputForm");
		return "redirect:/login";
	}

	/*
	 * { "name" : "Padmini", 
	 *   "dateOfBirth" : "30-08-2000",
	 *   "pan" : "PCASD1234Q",
	 *   "aadharNumber" : 102233445566,
	 *   "pensionType" : "family" }
	 */
	@PostMapping("/getPensionerDetail")
	public String fetchDetails(@ModelAttribute PensionerInput pensionerInput, BindingResult result, HttpSession session,
			ModelMap model) {

		log.info("start fetchDetails");
		
		log.debug(""+pensionerInput);
		PensionDetail pensionDetail=null;
try {
	pensionDetail = processPensionMicroserviceClient.getPensionDetails(pensionerInput);
		
}catch(Exception e)
{
	invalidAdhar=true;
	


	return "redirect:/processPensionerInput";
}
		if (pensionDetail == null ) {
			invalidInput = true;
			return "redirect:/processPensionerInput";
		}

		model.addAttribute("pensionDetail", pensionDetail);
		log.info("end fetchDetails");
		return "pensionDisbursement";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@GetMapping("/disbursement")
	public String successfulDisbursement(ModelMap model,HttpSession session) {
		
		log.info("start successfulDisbursement");
		String token=(String) session.getAttribute("token");
		JWTResponse response= new JWTResponse(token);

		boolean validated = authorizationMicroserviceClient.validateToken(response);//boolean validated = authorizationMicroserviceClient.validateToken((String) session.getAttribute("token"));
		if (validated) {
			model.addAttribute("msg", "Congratulations!!");
			model.addAttribute("info", "Amount has been disbursed to your bank account.");
			log.debug("success");
			return "success";
		}
		model.addAttribute("status","Error in disbursement");
		log.debug("failure");
		log.info("end successfulDisbursement");
		return "pensionDisbursement" ;
	}

}
