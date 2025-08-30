package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.DTO.UpdateSkillLevelDto;
import com.example.pathwisebackend.Models.Skill;
import com.example.pathwisebackend.Services.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
@CrossOrigin
public class SkillsController {
    private final SkillsService skillsService;

    @GetMapping("/recommended-resources/{id}")
    public List<Map<String, Object>> getRecommendedResources(@PathVariable("id") Long id) {
        return skillsService.getRecommendedResources(id);
    }

    @GetMapping("user-skills/{id}")
    public List<Skill> getUserSkills(@PathVariable("id") Long id) {
        return skillsService.getUserSkills(id);
    }

    @PostMapping("/update-score")
    public void updateScore(@RequestBody UpdateSkillLevelDto skillDto) {
        skillsService.updateSkillScore(skillDto);
    }
}
