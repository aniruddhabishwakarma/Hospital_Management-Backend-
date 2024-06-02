package com.example.HospitalManagement.System.service.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RolesService {
    ResponseEntity<Object> setRole(Long id);

    ResponseEntity<Object> setHospitalManager(Long id);

    ResponseEntity<Object> setUserRole(Long id);
}
