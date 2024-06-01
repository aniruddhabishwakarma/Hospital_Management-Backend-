package com.example.HospitalManagement.System.repository.user;

import com.example.HospitalManagement.System.entity.UserEntity;
import com.example.HospitalManagement.System.entity.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles,Integer> {
    @Query("SELECT u FROM UserRoles u WHERE u.userEntity.id = :id")
    UserRoles findUserRoleById(@Param("id") Long id);
}
