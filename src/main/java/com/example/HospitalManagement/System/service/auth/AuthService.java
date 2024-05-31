package com.example.HospitalManagement.System.service.auth;

import com.example.HospitalManagement.System.model.AuthRequest;
import com.example.HospitalManagement.System.model.Users;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<Object> registerUser(Users users);

    ResponseEntity<Object> login(AuthRequest authRequest);
}
