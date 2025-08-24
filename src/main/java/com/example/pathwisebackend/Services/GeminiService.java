package com.example.pathwisebackend.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GeminiService {

//    @Value("${gemini.api.key}")
//    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private String callGemini(String prompt) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=Key";

        // Request body
        Map<String, Object> content = new HashMap<>();
        content.put("parts", Collections.singletonList(Map.of("text", prompt)));
        Map<String, Object> request = Map.of("contents", Collections.singletonList(content));

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
            Map<String, Object> contentResp = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) contentResp.get("parts");
            String rawText =  (String) parts.get(0).get("text");

            String cleanedText = rawText
                    .replaceFirst("^\\s*```json\\s*", "")  // Remove opening ```json
                    .replaceFirst("\\s*```\\s*$", "");     // Remove closing ```

            return cleanedText.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing response: " + e.getMessage();
        }
    }

    public String runInterviewPrepFlow() {
        // 1️⃣ First query → skills with levels
        String firstPrompt = "I'm going to apply senior full stack engineer job. " +
                "What are the related skills I need to have and level(beginner, intermediate, Expert). " +
                "Give them in the array format. Give only skills array like below. not include any other information.\n\n" +
                "Ex: [ { skill: React, level: intermediate }, { skill: React, level: intermediate } ]";

        String skillsResponse = callGemini(firstPrompt);

        System.out.println("=== Skills Response ===");
        System.out.println(skillsResponse);

        // 2️⃣ Second query → course links based on skills
        String secondPrompt = "I want to be a full stack developer. I need to find courses to follow and " +
                "what are the skills I can gain. my current skills are given below and my current position is inter full stack developer.\n\n" +
                "my skills: nextjs, react, nest js, java, mongo db, postgres\n\n" +
                "give links of the courses and skills in following format:\n" +
                "[{ course_link: link of course, expected_skills: [react] }, { course_link: link of course, expected_skills: [react] }]\n\n" +
                "just give me this json. in the expected skills only mention technologies like skills as array. Give me minimum 5. " +
                "Give me exact link to the course.\n\n" +
                "Expected skills and skill levels given below.\n\n" +
                skillsResponse;

        String coursesResponse = callGemini(secondPrompt);

        System.out.println("=== Courses Response ===");
        System.out.println(coursesResponse);

        return coursesResponse; // return JSON to frontend
    }
}
