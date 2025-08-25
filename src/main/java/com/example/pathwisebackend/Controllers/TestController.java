package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.GeminiService;
import com.example.pathwisebackend.Services.SkillsService;
import com.example.pathwisebackend.Services.TestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path="test")
@CrossOrigin
public class TestController {
    private final TestService testService;
    private final GeminiService geminiService;
    private final SkillsService skillsService;

    public TestController(TestService testService, GeminiService geminiService, SkillsService skillsService) {
        this.testService = testService;
        this.geminiService = geminiService;
        this.skillsService = skillsService;
    }

    @GetMapping()
    public String test() {
      return this.testService.testServiceFunc();
    }

//    @GetMapping("/interview-prep")
//    public String interviewPrep() {
//        return geminiService.runInterviewPrepFlow(4l).toString();
//    }

    @GetMapping("/skills")
    public Map<String, Object> getSkills() {
        return skillsService.getRecommendedResources(4L);
    }
}
