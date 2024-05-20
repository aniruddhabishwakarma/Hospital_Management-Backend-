package com.example.HospitalManagement.System.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    @Value("${password.salt}")
    private String salt;

    public String encodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String saltedPassword = password + salt;
        return passwordEncoder.encode(saltedPassword);
    }

    public boolean matches(String enteredPassword, String storedHashedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String saltedPassword = enteredPassword + salt;
        return !passwordEncoder.matches(saltedPassword,storedHashedPassword);
    }
}
