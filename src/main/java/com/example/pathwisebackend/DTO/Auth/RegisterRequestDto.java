package com.example.pathwisebackend.DTO.Auth;

import com.example.pathwisebackend.Enum.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Contact number is required")
    private String contactNo;

    @NotNull(message = "Role is required")
    private UserRoles role;

    @NotBlank(message = "Address is required")
    private String address;

    private String currentPosition;
    private String currentIndustry;

    @Size(min = 1, message = "At least one industry is required")
    private IndustryDto[] industryList;

    @Size(min = 1, message = "At least one skill is required")
    private SkillsDto[] skillList;

    @Size(min = 1, message = "At least one experience is required")
    private ExperienceDto[] experienceList;

    @Size(min = 1, message = "At least one education is required")
    private EducationDto[] educationList;
}