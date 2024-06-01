package com.example.HospitalManagement.System.repository.photos;

import com.example.HospitalManagement.System.entity.PhotosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PhotosRepository extends JpaRepository<PhotosEntity,Integer> {

    @Query("SELECT p from PhotosEntity p WHERE p.userEntity.id = :userId")
    Optional<PhotosEntity> findByUserId(@Param("userId") Long userId);
}
