package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.GeminiService;
import com.example.pathwisebackend.Services.SkillsService;
import com.example.pathwisebackend.Services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="test")
@CrossOrigin
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final SkillsService skillsService;
    private final GeminiService geminiService;

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

    @GetMapping("/career-paths")
    public List<Map<String, Object>> getCareerPaths() {
        return geminiService.getCareerPaths(Arrays.asList("Senior Technical Lead", "HR manager"), "Intern software engineer");
    }
}
