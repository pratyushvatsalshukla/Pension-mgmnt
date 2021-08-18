package com.pensionManagementSystem.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pensionManagementSystem.client.AuthorizationMicroserviceClient;
import com.pensionManagementSystem.model.JWTResponse;
import com.pensionManagementSystem.model.User;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/portal")
@Controller
@Slf4j
public class LoginController {

	@Autowired
	AuthorizationMicroserviceClient authorizationMicroserviceClient;

	@GetMapping("/login")
	public String showLoginPage(@ModelAttribute User user) {
		
		log.info("start loginpage");
		
		log.info("end loginpage");
		
		return "login";
	}

	@PostMapping("/login")
	public String processLogin(@ModelAttribute User user, ModelMap model, HttpSession session) {

		log.info("start processLogin");
		ResponseEntity<?> token;
		try {
			token = authorizationMicroserviceClient.generateToken(user);
			HashMap<String, String> tokenBodyMap = (LinkedHashMap<String, String>) token.getBody();
			JWTResponse response = new JWTResponse(tokenBodyMap.get("token"));
			model.addAttribute("status", "Login Success!!");
			session.setAttribute("token", response.getToken());
			
			log.debug("Login Success");

		} catch (Exception e) {
			model.addAttribute("status", "Invalid Credentials!!");
			log.debug("Invalid Credentials!!");
			return "login";
		}
		
		log.info("end processLogin");
		return "redirect:/processPensionerInput";
	}
	
	@GetMapping("/logout")
	public String logoutUser(ModelMap model, HttpSession session) {
		
		log.info("start logout");
		
		String token=(String) session.getAttribute("token");
		JWTResponse response= new JWTResponse(token);

		boolean validated = authorizationMicroserviceClient.validateToken(response);
		
		if (validated) {
			model.addAttribute("status", "Loggedout Successfully!");
			log.debug("Loggedout Successfully!");
		} else {
			model.addAttribute("status", "Wrong User!!");
			log.debug("wrong user");
		}
		session.invalidate();
		log.info("end logout");
		return "login";
	}

}
