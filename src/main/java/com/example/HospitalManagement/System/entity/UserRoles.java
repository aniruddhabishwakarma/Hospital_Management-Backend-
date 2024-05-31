package com.example.HospitalManagement.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
public class UserRoles implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

    public UserRoles(int id, int userEntityId, int roleId) {
        this.id = id;
        // Assuming you have setters for userEntity and roles
        this.userEntity.setId(userEntityId); // Directly setting the ID might not be possible depending on your entity relationships
        this.roles.setId(roleId); // Similar caution applies here
    }

}
