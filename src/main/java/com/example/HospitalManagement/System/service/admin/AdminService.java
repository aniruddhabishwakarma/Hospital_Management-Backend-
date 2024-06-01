package com.example.HospitalManagement.System.service.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    ResponseEntity<Object> setRole(Long id);

    ResponseEntity<Object> setHospitalManager(Long id);
}
