package com.example.HospitalManagement.System.service.user;

import com.example.HospitalManagement.System.entity.PhotosEntity;
import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.model.Response;
import com.example.HospitalManagement.System.repository.photos.PhotosRepository;
import com.example.HospitalManagement.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    @Value("${project.image}")
    private String path;

    private final UsersRepository usersRepository;

    private final PhotosRepository photosRepository;

    @Override
    public ResponseEntity<Object> addPhoto(MultipartFile file, Long id) {
        try {
            String originalFileName = file.getOriginalFilename();
            String extension = "";
            int lastDotIndex = originalFileName.lastIndexOf('.');
            if (lastDotIndex != -1) {
                extension = originalFileName.substring(lastDotIndex);
            }

            String fileName = "user" + UUID.randomUUID() + extension;
            String filePath = path + File.separator + fileName;

            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
            Files.copy(file.getInputStream(), Paths.get(filePath));

            Optional<UserEntity> userEntityOpt = usersRepository.findById(id);
            UserEntity userEntity = userEntityOpt.get();
            PhotosEntity photosEntity = PhotosEntity.builder()
                    .name(fileName)
                    .userEntity(userEntity)
                    .build();
            photosRepository.save(photosEntity);
            return new ResponseEntity<>(new Response("Photo has been added successfully"), HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//
}
