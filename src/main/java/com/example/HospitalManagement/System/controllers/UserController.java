package com.example.HospitalManagement.System.controllers;

import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/addPhoto")
    public ResponseEntity<Object> addPhoto(@RequestParam("photo") MultipartFile file){
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = user.getId();
        return userService.addPhoto(file, id);
    }

    @PutMapping("/user/updatePhoto")
    public ResponseEntity<Object> updatePhoto(@RequestParam("photo") MultipartFile file){
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = user.getId();
        return userService.updatePhoto(file, id);
    }

    @GetMapping("/photo")
    public ResponseEntity<?> displayPhoto(@RequestParam("fileName") String fileName){
        return userService.returnPhoto(fileName);
    }
}
