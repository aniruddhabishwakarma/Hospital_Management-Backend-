package com.example.HospitalManagement.System.controllers;

import com.example.HospitalManagement.System.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PutMapping("/adminRole/{id}")
    public ResponseEntity<Object> setAdminRole(@PathVariable("id") Long id){

        return adminService.setRole(id);
    }

    @PutMapping("/managerRole/{id}")
    public ResponseEntity<Object> setHospitalManager(@PathVariable("id") Long id){
        return adminService.setHospitalManager(id);
    }
}
