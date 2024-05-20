package com.example.HospitalManagement.System.service.user;

import com.example.HospitalManagement.System.config.PasswordEncoder;
import com.example.HospitalManagement.System.entity.Roles;
import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.model.Response;
import com.example.HospitalManagement.System.model.Users;
import com.example.HospitalManagement.System.repository.RolesRepository;
import com.example.HospitalManagement.System.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImplementation implements UsersService{

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    @Override
    public ResponseEntity<Object> registerUser(Users users) {
        if(users.getId() == null || users.getId().isEmpty()){
            users.setId(UUID.randomUUID().toString());
        }

        if(usersRepository.existsByUsername(users.getUsername())){
            throw new IllegalArgumentException("Username " + users.getUsername() + " already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(users.getId());
        userEntity.setFullName(users.getFullName());
        userEntity.setEmail(users.getEmail());
        userEntity.setContact(userEntity.getContact());
        userEntity.setPassword(new PasswordEncoder().encodePassword(users.getPassword()));
        Roles roles = rolesRepository.findByName("ROLE_USER").get();
        userEntity.setRoles(List.of(roles));
        usersRepository.save(userEntity);
        return new ResponseEntity<>(new Response("User has been registered successfully"), HttpStatus.OK);
    }
}
