package com.example.security.registration.token;

import java.time.LocalDateTime;

import com.example.security.appuser.AppUser;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

public class ConfirmationToken {
	@SequenceGenerator( name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1)
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence")
	private Long id;
	
	 @Column(nullable = false)
	private String token;
	 
	 @Column(nullable = false)
	private LocalDateTime createdAt;
	 
	 @Column(nullable = false)
	private LocalDateTime expiresAt;
	private LocalDateTime confirmedAt;
	@ManyToOne
	@JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
	private AppUser appUser;
	
	public ConfirmationToken()
	{
		
	}
	public ConfirmationToken(String token,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            AppUser appUser)
	{
		 this.token = token;
	        this.createdAt = createdAt;
	        this.expiresAt = expiresAt;
	        this.appUser = appUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
}
