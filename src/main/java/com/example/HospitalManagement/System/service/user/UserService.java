package com.example.HospitalManagement.System.service.user;

import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.model.user.Password;
import com.example.HospitalManagement.System.model.user.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
    ResponseEntity<Object> addPhoto(MultipartFile file, Long id);

    ResponseEntity<Object> updatePhoto(MultipartFile file, Long id);

    ResponseEntity<?> returnPhoto(String fileName);

    ResponseEntity<Object> userInfo(Long id);

    ResponseEntity<Object> updateInfo(UserInfo userInfo);

    ResponseEntity<Object> updatePassword(Password password);
}
