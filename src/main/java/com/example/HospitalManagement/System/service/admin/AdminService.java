package com.example.HospitalManagement.System.service.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    ResponseEntity<Object> setRole(String id);

    ResponseEntity<Object> setHospitalManager(String id);
}
