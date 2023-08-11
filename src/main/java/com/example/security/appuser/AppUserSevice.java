package com.example.security.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.security.registration.token.ConfirmationToken;
import com.example.security.registration.token.ConfirmationTokenSevice;

import jakarta.transaction.Transactional;
@Service
public class AppUserSevice implements UserDetailsService {
	
	private final static String USE_NOT_FOUND ="user with email %s not found";
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ConfirmationTokenSevice confirmationTokenSevice;
	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email).orElseThrow(
				()-> new UsernameNotFoundException(String.format(USE_NOT_FOUND,email))
				);
	}

	@Transactional
	public String SignUpUser(AppUser appUser) {
		boolean isVaidUser = appUserRepository.findByEmail(appUser.getEmail()).isPresent() ;
		if(isVaidUser)
		{
			throw new IllegalStateException("email already taken");
		}
		String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodePassword);
		
		appUserRepository.save(appUser);
		
		String token = UUID.randomUUID().toString();
		ConfirmationToken confimationtoken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				 LocalDateTime.now().plusMinutes(15),
	                appUser
				);
		confirmationTokenSevice.saveToken(confimationtoken);
		return token;
		
	}
	public int enableAppUser(String email)
	{
		return appUserRepository.enableAppUser(email);
	}
	
}
