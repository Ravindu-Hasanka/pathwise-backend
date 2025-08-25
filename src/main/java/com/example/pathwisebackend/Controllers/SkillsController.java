package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.SkillsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(name = "/skills")
public class SkillsController {
    private final SkillsService skillsService;

    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping("/recommended-resources/{id}")
    public List<Map<String, Object>> getRecommendedResources(@RequestParam Long id) {
        return skillsService.getRecommendedResources(id);
    }
}
