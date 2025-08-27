package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.GeminiService;
import com.example.pathwisebackend.Services.SkillsService;
import com.example.pathwisebackend.Services.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="test")
@CrossOrigin
public class TestController {
    private final TestService testService;
    private final SkillsService skillsService;

    public TestController(TestService testService, GeminiService geminiService, SkillsService skillsService) {
        this.testService = testService;
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

    @GetMapping("/skills/{id}")
    public List<Map<String, Object>> getSkills(@RequestParam Long id) {
        return skillsService.getRecommendedResources(id);
    }

    @GetMapping("/jobs/{id}")
    public List<Map<String, Object>> getJobs(@RequestParam Long id) {
        return skillsService.getRecommendedJobs(id);
    }
}
