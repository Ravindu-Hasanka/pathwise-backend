package com.example.pathwisebackend.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "coaches")
@PrimaryKeyJoinColumn(name = "userId")
public class Coach extends User {

    private String description;

//    @ManyToMany
//    @JoinTable(
//            name = "industries",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "industry_id")
//    )
//    private Set<Industry> industries = new HashSet<>();
}
