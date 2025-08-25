package com.example.pathwisebackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IndustryDto {
    private Long industryId;
    private String name;
    private List<JobRoleDto> jobRoles;
}
