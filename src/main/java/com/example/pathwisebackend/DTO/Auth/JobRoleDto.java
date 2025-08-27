package com.example.pathwisebackend.DTO.Auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JobRoleDto {
    Long jobRoleId;
    String jobRoleName;
    Double minSalary;
    Double maxSalary;
    Double hourlyConsultingSalary;
}
