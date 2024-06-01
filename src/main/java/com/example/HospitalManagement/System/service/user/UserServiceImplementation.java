package com.example.HospitalManagement.System.service.user;

import com.example.HospitalManagement.System.entity.PhotosEntity;
import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.model.Response;
import com.example.HospitalManagement.System.repository.photos.PhotosRepository;
import com.example.HospitalManagement.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Optional<PhotosEntity> photosEntityOptional = photosRepository.findByUserId(id);
            if(photosEntityOptional.isPresent()){
                throw new IllegalArgumentException("Photo already exists");
            }

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
                    .name("/api/photo?fileName=" + fileName)
                    .userEntity(userEntity)
                    .build();
            photosRepository.save(photosEntity);
            return new ResponseEntity<>(new Response("Photo has been added successfully"), HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Object> updatePhoto(MultipartFile file, Long id) {
        try {
            Optional<PhotosEntity> photosEntityOptional = photosRepository.findByUserId(id);
            if(photosEntityOptional.isPresent()){
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
                PhotosEntity photosEntity = photosEntityOptional.get();
                photosEntity.setName("/api/photo?fileName=" + fileName);
                photosRepository.save(photosEntity);
                return new ResponseEntity<>(new Response("Photo has been updated successfully"), HttpStatus.OK);
            }else{
                throw new IllegalArgumentException("Photo not found ");
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResponseEntity<?> returnPhoto(String fileName) {
        String filePath = path + File.separator+fileName;
        Logger.getAnonymousLogger().log(Level.FINE,filePath);
        try{
            InputStream inputStream = new FileInputStream(filePath);
            byte[] out = IOUtils.toByteArray(inputStream);
            HttpHeaders responseHeaders=new HttpHeaders();
            responseHeaders.set("charset","utf-8");
            responseHeaders.setContentType(MediaType.IMAGE_JPEG);
            return  new ResponseEntity<>(out,responseHeaders,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Response.builder().message(e.getMessage()));
        }
    }

}
