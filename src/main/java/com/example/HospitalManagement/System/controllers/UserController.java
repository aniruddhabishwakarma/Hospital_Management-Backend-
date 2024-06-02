package com.example.HospitalManagement.System.controllers;

import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.model.user.Password;
import com.example.HospitalManagement.System.model.user.UserInfo;
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

    @GetMapping("/user/info")
    public ResponseEntity<Object> getUserInfo(){
       UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       Long id = user.getId();
       return userService.userInfo(id);
    }

    @PutMapping("/user/updateInfo")
    public ResponseEntity<Object> updateInfo(@RequestBody UserInfo userInfo){
        return userService.updateInfo(userInfo);
    }

    @PutMapping("/user/updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestBody Password password){
        return userService.updatePassword(password);
    }

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
