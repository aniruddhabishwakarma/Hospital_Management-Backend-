package com.example.HospitalManagement.System.controllers;

import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/addPhoto")
    public ResponseEntity<Object> addPhoto(@RequestParam("photo") MultipartFile file){
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = user.getId();
        return userService.addPhoto(file, id);
    }
}
