package com.example.security.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	@PostMapping("api/v1/registration")
	public String register (@RequestBody RegistrationRequest registrationRequest) 
	{
		
		return registrationService.register(registrationRequest);
	}
	@GetMapping("api/v1/registration/confirm")
	public String confirm(@RequestParam("token") String token )
	{
		return registrationService.confirm(token);
	}
	
}
