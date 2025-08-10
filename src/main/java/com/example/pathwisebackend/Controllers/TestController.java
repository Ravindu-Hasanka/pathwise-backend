package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.TestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="test")
@CrossOrigin
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping()
    public String test() {
      return this.testService.testServiceFunc();
    }
}
