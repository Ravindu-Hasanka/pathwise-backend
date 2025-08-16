package com.example.pathwisebackend.DTO;

import com.example.pathwisebackend.DTO.User.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CoachProfileDTO extends UserDTO {
    private String description;
}
