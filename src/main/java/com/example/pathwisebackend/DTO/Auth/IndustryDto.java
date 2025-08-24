package com.example.pathwisebackend.DTO.Auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class IndustryDto {
    Long industryId;
    String industryName;
    JobRoleDto[] jobRoleDtos;
}
