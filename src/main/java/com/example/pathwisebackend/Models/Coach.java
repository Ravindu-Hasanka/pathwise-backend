package com.example.pathwisebackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "coaches")
@PrimaryKeyJoinColumn(name = "userId")
public class Coach extends User {

    private String description;
}
