package com.example.pathwisebackend.DTO.Auth;

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
