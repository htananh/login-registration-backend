package com.example.security.Security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security.appuser.AppUserSevice;

@Configuration
public class WebServiceConfig {
	@Autowired
	private AppUserSevice appUserSevice;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 return http.csrf().disable()
				 .authorizeHttpRequests()
				 .requestMatchers("/api/v*/registration/**").permitAll()
				
				 .anyRequest()
				 .authenticated().and()
				 .sessionManagement()
				 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				 .and().build();
	 }
	 @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(appUserSevice);
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
}
