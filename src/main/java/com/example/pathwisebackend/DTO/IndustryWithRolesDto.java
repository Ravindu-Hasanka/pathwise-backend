package com.example.pathwisebackend.DTO;

import java.util.List;

public record IndustryWithRolesDto(
        String industryName,
        List<String> jobRoles
) {}
