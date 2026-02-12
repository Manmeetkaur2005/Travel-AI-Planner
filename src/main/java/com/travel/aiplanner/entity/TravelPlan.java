package com.travel.aiplanner.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "travel_plans")
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;
    private String duration;
    private String budget;
    private String interests;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String aiGeneratedPlan;

    // CONSTRUCTORS
    public TravelPlan() {
    }

    public TravelPlan(String source,String destination, String duration, String budget, String interests, String aiGeneratedPlan) {
        this.source = source;
        this.destination = destination;
        this.duration = duration;
        this.budget = budget;
        this.interests = interests;
        this.aiGeneratedPlan = aiGeneratedPlan;
    }

    // --- GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getInterests() { return interests; }
    public void setInterests(String interests) { this.interests = interests; }

    public String getAiGeneratedPlan() { return aiGeneratedPlan; }
    public void setAiGeneratedPlan(String aiGeneratedPlan) { this.aiGeneratedPlan = aiGeneratedPlan; }
}
