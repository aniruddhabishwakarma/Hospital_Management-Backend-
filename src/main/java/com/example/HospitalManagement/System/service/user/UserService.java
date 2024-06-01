package com.example.HospitalManagement.System.service.user;

import com.example.HospitalManagement.System.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
    ResponseEntity<Object> addPhoto(MultipartFile file, Long id);
}
