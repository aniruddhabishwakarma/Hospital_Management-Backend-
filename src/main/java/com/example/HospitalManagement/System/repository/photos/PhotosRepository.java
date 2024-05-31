package com.example.HospitalManagement.System.repository.photos;

import com.example.HospitalManagement.System.entity.PhotosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository extends JpaRepository<PhotosEntity,Integer> {
}
