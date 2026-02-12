package com.travel.aiplanner.Service;

import com.travel.aiplanner.entity.TravelPlan;

import java.util.List;

public interface TravelPlanService {
    TravelPlan createTravelPlan(TravelPlan travelPlan);
    List<TravelPlan> getAllTravelPlans();
    String getAiResponse(String prompt);
}
