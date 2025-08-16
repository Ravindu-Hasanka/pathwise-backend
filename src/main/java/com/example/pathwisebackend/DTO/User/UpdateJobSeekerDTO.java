package com.example.pathwisebackend.DTO.User;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateJobSeekerDTO extends UpdateUserDTO {

    @Nullable
    @Size(min = 2, max = 100, message = "Current position must be between 2 and 100 characters")
    private String currentPosition;

    @Nullable
    @Size(min = 2, max = 100, message = "Current industry must be between 2 and 100 characters")
    private String currentIndustry;
}
