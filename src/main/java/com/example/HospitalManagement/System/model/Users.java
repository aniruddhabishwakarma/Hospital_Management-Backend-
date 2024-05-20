package com.example.HospitalManagement.System.model;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private String id;
    private String fullName;
    private String username;
    private String email;
    private String contact;
    private String password;
}
