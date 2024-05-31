package com.example.HospitalManagement.System.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @NotNull(message = "Cannot be null or empty")
    @NotEmpty(message = "Cannot be empty")
    private String fullName;
    @NotNull(message = "Cannot be null or empty")
    @NotEmpty(message = "Cannot be empty")
    @Size(min = 5,  message = "Username must be minimum 5 length")
    private String username;
    @NotNull(message = "Cannot be null or empty")
    @NotEmpty(message = "Cannot be empty")
    private String email;
    @NotNull(message = "Cannot be null or empty")
    @NotEmpty(message = "Cannot be empty")
    @Pattern(regexp = "^9\\d{9}$", message = "Contact number must start with '9' and be exactly 10 digits long.")
    private String contact;
    @NotNull(message = "Cannot be null or empty")
    @NotEmpty(message = "Cannot be empty")
    private String password;
}
