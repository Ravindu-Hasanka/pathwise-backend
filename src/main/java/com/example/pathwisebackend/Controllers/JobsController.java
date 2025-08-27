package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/jobs")
@RequiredArgsConstructor
@CrossOrigin
public class JobsController {
    private final SkillsService skillsService;

    @GetMapping("/recommended-jobs/{id}")
    public List<Map<String, Object>> getJobs(@PathVariable Long id) {
        return skillsService.getRecommendedJobs(id);
    }
}
