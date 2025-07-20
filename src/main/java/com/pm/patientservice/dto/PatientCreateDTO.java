package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientCreateDTO(
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    String name,
    
    @NotBlank(message = "email is required")
    @Email(message = "Email should be valid")
    String email,

    @NotBlank(message = "Address is required")
    String address,

    @NotBlank(message = "Date of birth is required")
    String dateOfBirth,

    @NotBlank(message = "Registered Date is required")
    String registeredDate
    ) {
    
}
