package com.example.security.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenSevice {
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveToken(ConfirmationToken token)
	{
		 confirmationTokenRepository.save(token);
	}
	 public Optional<ConfirmationToken> getToken(String token)
	 {
	        return confirmationTokenRepository.findByToken(token);
	 }
	public void setConfirm(String token)
	{
		confirmationTokenRepository.updateConfirmedAt(token,LocalDateTime.now());
	}
}
