package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.Repositories.TestRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {
private final TestRepository testRepository;
public TestService(TestRepository testRepository) {
    this.testRepository = testRepository;
}

public String testServiceFunc(){
    return "Hello World";
}
}
