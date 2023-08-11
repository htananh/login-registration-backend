package com.example.security.registration;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String>{

	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public boolean test(String email) {
        return email.matches(EMAIL_REGEX);
    }

}
