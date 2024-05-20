package com.example.HospitalManagement.System.controllers;

import com.example.HospitalManagement.System.model.Users;
import com.example.HospitalManagement.System.service.user.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;

    public ResponseEntity<Object> registerUser(@RequestBody Users users){

        return usersService.registerUser(users);
    }
}
