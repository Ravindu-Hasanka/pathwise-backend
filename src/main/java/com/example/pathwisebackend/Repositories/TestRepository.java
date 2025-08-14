package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, String> {

}
