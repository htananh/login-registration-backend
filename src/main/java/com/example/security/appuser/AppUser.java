package com.example.security.appuser;


import java.util.Collection;
import java.util.Collections;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@EqualsAndHashCode

@Entity
public class AppUser implements UserDetails {
	
	
	
	
	@SequenceGenerator(  name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_sequence")
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	@Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
	private Boolean locked = false;
	private Boolean enabled = false;
	
	public AppUser() {}
	public AppUser(String firstName,
            String lastName,
            String email,
            String password,
            AppUserRole appUserRole) {
	 this.firstName = firstName;
	 this.lastName = lastName;
	 this.email = email;
	 this.password = password;
	 this.appUserRole = appUserRole;
}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singleton(authority);
	}
	@Override
	public String getPassword() {
		
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	 public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AppUserRole getAppUserRole() {
		return appUserRole;
	}

	public void setAppUserRole(AppUserRole appUserRole) {
		this.appUserRole = appUserRole;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
