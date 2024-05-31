package com.example.HospitalManagement.System.repository.user;

import com.example.HospitalManagement.System.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.contact = :contact")
    Optional<UserEntity> findByContact(@Param("contact") String contact);

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);
}
