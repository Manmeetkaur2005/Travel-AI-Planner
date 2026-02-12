package com.travel.aiplanner.repository;

import com.travel.aiplanner.entity.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPlanRepository extends JpaRepository<TravelPlan, Long> {

}
