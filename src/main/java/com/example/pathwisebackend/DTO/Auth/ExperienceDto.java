package com.example.pathwisebackend.DTO.Auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ExperienceDto {
    Long experienceId;
    String jobTitle;
    String companyName;
    Date startedAt;
    Date endedAt;
}
