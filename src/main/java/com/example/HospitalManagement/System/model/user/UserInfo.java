package com.example.HospitalManagement.System.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String contact;
    private String name;

}
