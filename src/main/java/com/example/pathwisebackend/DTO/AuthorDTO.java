package com.example.pathwisebackend.DTO;

import com.example.pathwisebackend.Enum.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String email;
    private UserRoles role;
}
