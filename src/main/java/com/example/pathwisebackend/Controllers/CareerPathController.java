package com.example.pathwisebackend.Controllers;

import com.example.pathwisebackend.Services.CareerPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(path = "/api/career-path")
@RequiredArgsConstructor
@CrossOrigin
public class CareerPathController {
    private final CareerPathService careerPathService;

    @GetMapping("/get-carrer-path/{id}")
    public List<Map<String, Object>> getCarrerPath(@PathVariable Long id) {
        return careerPathService.getCareerPaths(id);
    }
}
