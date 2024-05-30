package com.example.HospitalManagement.System.service.user;

import com.example.HospitalManagement.System.config.PasswordEncoder;
import com.example.HospitalManagement.System.entity.Roles;
import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.jwt.JwtTokenUtil;
import com.example.HospitalManagement.System.model.AuthRequest;
import com.example.HospitalManagement.System.model.AuthResponse;
import com.example.HospitalManagement.System.model.Response;
import com.example.HospitalManagement.System.model.Users;
import com.example.HospitalManagement.System.repository.user.RolesRepository;
import com.example.HospitalManagement.System.repository.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImplementation implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    private final JwtTokenUtil jwtTokenUtil;

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
        userEntity.setUsername(users.getUsername());
        userEntity.setEmail(users.getEmail());
        userEntity.setContact(users.getContact());
        userEntity.setPassword(new PasswordEncoder().encodePassword(users.getPassword()));
        Roles roles = rolesRepository.findByName("ROLE_USER").get();
        userEntity.setRoles(List.of(roles));
        usersRepository.save(userEntity);
        return new ResponseEntity<>(new Response("User has been registered successfully"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> login(AuthRequest authRequest) {
        Optional<UserEntity> userEntity = usersRepository.findByUsername(authRequest.getUsername());

        if(!userEntity.isPresent()){
            throw new UsernameNotFoundException("Username " + authRequest.getUsername()+ " doesn't exist");
        }
        if(new PasswordEncoder().matches(authRequest.getPassword(), userEntity.get().getPassword())){
            throw new BadCredentialsException("Password invalid");
        }
        return ResponseEntity.ok(loginUser(userEntity.get()));
    }

    private AuthResponse loginUser(UserEntity userEntity) {
        return new AuthResponse(jwtTokenUtil.generateToken(userEntity));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = usersRepository.findByUsername(username);
        if(userEntityOptional.isPresent()){
            return userEntityOptional.get();
        }
        throw new UsernameNotFoundException("username " + username + " not found");
    }
}
