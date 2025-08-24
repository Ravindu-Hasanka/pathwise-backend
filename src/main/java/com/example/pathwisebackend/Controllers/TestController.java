package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.GeminiService;
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
    private final GeminiService geminiService;

    public TestController(TestService testService, GeminiService geminiService) {
        this.testService = testService;
        this.geminiService = geminiService;
    }

    @GetMapping()
    public String test() {
      return this.testService.testServiceFunc();
    }

    @GetMapping("/interview-prep")
    public String interviewPrep() {
        return geminiService.runInterviewPrepFlow();
    }
}
