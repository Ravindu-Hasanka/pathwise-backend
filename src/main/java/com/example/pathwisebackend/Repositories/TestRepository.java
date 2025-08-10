package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends MongoRepository<Test, String> {

}
