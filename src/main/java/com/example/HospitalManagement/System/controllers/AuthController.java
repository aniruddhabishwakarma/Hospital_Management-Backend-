package com.example.HospitalManagement.System.controllers;

import com.example.HospitalManagement.System.model.auth.AuthRequest;
import com.example.HospitalManagement.System.model.user.Users;
import com.example.HospitalManagement.System.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody Users users){
        System.out.println(users + "from controller");
        return authService.registerUser(users);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody AuthRequest authRequest){
        return authService.login(authRequest);
    }
}
