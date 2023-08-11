package com.example.security.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements EmailSender{

	@Autowired
	private JavaMailSender mailSender;
	@Override
	@Async
	public void send(String to, String email) {
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage(); 
			MimeMessageHelper helper =
	                new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("21521818@gm.uit.edu.vn");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			 throw new IllegalStateException("failed to send email");
		}
		
		
		
	}

}
