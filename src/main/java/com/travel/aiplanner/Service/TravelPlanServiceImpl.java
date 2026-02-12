package com.travel.aiplanner.Service;

import com.travel.aiplanner.entity.TravelPlan;
import com.travel.aiplanner.repository.TravelPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class TravelPlanServiceImpl implements TravelPlanService {

    @Autowired
    private TravelPlanRepository travelPlanRepository;


    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Override
    public TravelPlan createTravelPlan(TravelPlan travelPlan) {
        return travelPlanRepository.save(travelPlan);
    }

    @Override
    public List<TravelPlan> getAllTravelPlans() {
        return travelPlanRepository.findAll();
    }

    @Override
    public String getAiResponse(String prompt) {
        try {

            String finalUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=" + geminiApiKey.trim();

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String requestJson = "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + prompt + "\" }] }] }";
            HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(finalUrl, request, Map.class);

            Map<String, Object> body = response.getBody();
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

            return parts.get(0).get("text").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI Error: " + e.getMessage();
        }
    }
}