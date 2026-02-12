package com.travel.aiplanner.controller;

import com.travel.aiplanner.Service.TravelPlanService; // Capital 'S' retained
import com.travel.aiplanner.entity.TravelPlan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
@CrossOrigin(origins = "*")
public class TravelPlanController {

    private final TravelPlanService travelPlanService;

    public TravelPlanController(TravelPlanService travelPlanService) {
        this.travelPlanService = travelPlanService;
    }

    @PostMapping("/create")
    public ResponseEntity<TravelPlan> createTravelPlan(@RequestBody TravelPlan plan) {

        // 1. Prompt for ai
        String prompt = "Plan a trip from " + plan.getSource() +
                " to " + plan.getDestination() +
                " for " + plan.getDuration() +
                " with budget " + plan.getBudget() +
                " interested in " + plan.getInterests();

        // 2. AI calling and getting response
        String aiResponse = travelPlanService.getAiResponse(prompt);

        // 3. Setting Response
        plan.setAiGeneratedPlan(aiResponse);

        // 4. Save to Database
        TravelPlan createdPlan = travelPlanService.createTravelPlan(plan);

        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TravelPlan>> getAllTravelPlans(){
        List<TravelPlan> plans = travelPlanService.getAllTravelPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }
}