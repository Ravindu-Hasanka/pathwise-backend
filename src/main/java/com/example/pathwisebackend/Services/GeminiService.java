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

    public List<Map<String, Object>> runInterviewPrepFlow(List<IndustryDto> industryList) {
        List<Map<String, Object>> recommendedResources = new ArrayList<>();

        for (IndustryDto industry : industryList) {
            for (JobRoleDto jobRole : industry.getJobRoles()) {

                // Ask Gemini about recommended courses
                String prompt = "I want to become a " + jobRole.getJobRoleName() + ". " +
                        "I need to find courses, books, and certifications to follow and what skills I can gain. " +
                        "My current skills are: nextjs, react, nest js, java, mongo db, postgres. " +
                        "My current position is intermediate full stack developer.\n\n" +
                        "Give links of the courses/books/certifications and skills in the following format:\n" +
                        "[{ \"title\": \"Course Name\", \"type\": \"Course/Book/Certification\", \"provider\": \"Provider Name\", \"duration\": \"6h/320 pages/etc.\", \"link\": \"https://...\", \"skill\": \"React\" }]\n\n" +
                        "Give me at least 5 recommendations.";

                String response = callGemini(prompt);

                try {
                    Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                    List<Map<String, Object>> resources = gson.fromJson(response, listType);

                    // Add the resources to the final list
                    recommendedResources.addAll(resources);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return recommendedResources;
    }

}
