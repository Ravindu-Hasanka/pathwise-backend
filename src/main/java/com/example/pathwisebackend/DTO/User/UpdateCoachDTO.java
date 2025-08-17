package com.example.pathwisebackend.DTO.User;

import jakarta.persistence.ElementCollection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateCoachDTO extends UserDTO {
    private String description;
}
