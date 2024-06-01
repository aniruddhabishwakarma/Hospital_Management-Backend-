package com.example.HospitalManagement.System.service.admin;

import com.example.HospitalManagement.System.entity.Roles;
import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.entity.UserRoles;
import com.example.HospitalManagement.System.model.Response;
import com.example.HospitalManagement.System.repository.user.RolesRepository;
import com.example.HospitalManagement.System.repository.user.UserRolesRepository;
import com.example.HospitalManagement.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService{

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    private final UserRolesRepository userRolesRepository;
    @Override
    public ResponseEntity<Object> setRole(Long id) {


        Roles roles = rolesRepository.findByName("ROLE_ADMIN").get();

        UserRoles userRoles = userRolesRepository.findUserRoleById(id);
        userRoles.setRoles(roles);
        userRolesRepository.save(userRoles);
        return new ResponseEntity<>(new Response("Role has been assigned"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> setHospitalManager(Long id) {
        Roles roles = rolesRepository.findByName("ROLE_HOSPITAL_MANAGER").get();
        UserRoles userRoles = userRolesRepository.findUserRoleById(id);
        userRoles.setRoles(roles);
        userRolesRepository.save(userRoles);
        return new ResponseEntity<>(new Response("Role has been assigned"), HttpStatus.OK);
    }
}
