package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "related_skills")
public class RelatedSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long relatedSkillsId;

    @NotBlank(message = "Skill name should not be blank")
    private String name;
}
