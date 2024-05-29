package com.example.HospitalManagement.System.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String username;
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String password;
}
