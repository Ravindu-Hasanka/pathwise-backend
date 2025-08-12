package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
