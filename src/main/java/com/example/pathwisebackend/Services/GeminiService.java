package com.example.pathwisebackend.Services;

import com.example.pathwisebackend.DTO.CourseDto;
import com.example.pathwisebackend.DTO.IndustryDto;
import com.example.pathwisebackend.DTO.JobRoleDto;
import com.example.pathwisebackend.DTO.SkillDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();

    private String callGemini(String prompt) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        Map<String, Object> content = new HashMap<>();
        content.put("parts", Collections.singletonList(Map.of("text", prompt)));
        Map<String, Object> request = Map.of("contents", Collections.singletonList(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
            Map<String, Object> contentResp = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) contentResp.get("parts");
            String rawText = (String) parts.get(0).get("text");

            String cleanedText = rawText
                    .replaceFirst("^\\s*```json\\s*", "")
                    .replaceFirst("\\s*```\\s*$", "")
                    .trim();

            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\[.*]", java.util.regex.Pattern.DOTALL)
                    .matcher(cleanedText);
            if (matcher.find()) {
                cleanedText = matcher.group(0);
            }

            if (cleanedText.startsWith("\"") && cleanedText.endsWith("\"")) {
                cleanedText = cleanedText.substring(1, cleanedText.length() - 1);
                cleanedText = cleanedText.replace("\\\"", "\"");
            }
            return cleanedText.trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }

    public Map<String, Object> runInterviewPrepFlow(List<IndustryDto> industryList) {
        Map<String, Object> recommendations = new HashMap<>();

        for (IndustryDto industry : industryList) {
            List<Map<String, Object>> jobRoleRecommendations = new ArrayList<>();

            for (JobRoleDto jobRole : industry.getJobRoles()) {
                // Ask Gemini about skills
                String firstPrompt = "I'm going to apply for a " + jobRole.getJobRoleName() + " job. " +
                        "What are the related skills I need to have and level (beginner, intermediate, expert). " +
                        "Give them in JSON array format only.\n\n" +
                        "[{ \"skill\": \"React\", \"level\": \"intermediate\" }]";

                String skillsResponse = callGemini(firstPrompt);

                Type skillsType = new TypeToken<List<SkillDto>>() {}.getType();
                List<SkillDto> skills = gson.fromJson(skillsResponse, skillsType);

                // Ask Gemini about recommended courses
                String secondPrompt = "I want to become a " + jobRole.getJobRoleName() + ". " +
                        "I need to find courses to follow and what skills I can gain. " +
                        "My current skills are: nextjs, react, nest js, java, mongo db, postgres. " +
                        "My current position is intermediate full stack developer.\n\n" +
                        "Give links of the courses and skills in following format:\n" +
                        "[{ \"course_link\": \"https://...\", \"expected_skills\": [\"React\", \"Java\"] }]\n\n" +
                        "Give me at least 5.";

                String coursesResponse = callGemini(secondPrompt);

                Type coursesType = new TypeToken<List<CourseDto>>() {}.getType();
                List<CourseDto> courses = gson.fromJson(coursesResponse, coursesType);

                // Update jobRole DTO
                jobRole.setSkills(skills);
                jobRole.setCourses(courses);

                // Add to recommendations map
                Map<String, Object> jobRoleData = new HashMap<>();
                jobRoleData.put("jobRoleName", jobRole.getJobRoleName());
                jobRoleData.put("skills", skills);
                jobRoleData.put("courses", courses);

                jobRoleRecommendations.add(jobRoleData);
            }

            recommendations.put(industry.getName(), jobRoleRecommendations);
        }

        return recommendations;
    }
}
