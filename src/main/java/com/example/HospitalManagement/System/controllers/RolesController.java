package com.example.HospitalManagement.System.controllers;

import com.example.HospitalManagement.System.service.admin.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;


    @PutMapping("/adminRole/{id}")
    public ResponseEntity<Object> setAdminRole(@PathVariable("id") Long id){

        return rolesService.setRole(id);
    }

    @PutMapping("/managerRole/{id}")
    public ResponseEntity<Object> setHospitalManager(@PathVariable("id") Long id){
        return rolesService.setHospitalManager(id);
    }

    @PutMapping("/userRole/{id}")
    public ResponseEntity<Object> setUserRole(@PathVariable("id") Long id){
        return rolesService.setUserRole(id);
    }
}
