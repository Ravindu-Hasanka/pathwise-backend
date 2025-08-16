package com.example.pathwisebackend.DTO.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateCoachDTO extends CreateUserDTO {
    private String description;
}
