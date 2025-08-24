package com.example.pathwisebackend.DTO.Auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class EducationDto {
    Long educationId;
    String educationName;
    String institute;
    Date startedAt;
    Date endedAt;
}
