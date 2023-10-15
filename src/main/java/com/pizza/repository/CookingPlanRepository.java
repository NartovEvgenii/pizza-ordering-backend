package com.pizza.repository;

import com.pizza.domains.CookingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface CookingPlanRepository  extends JpaRepository<CookingPlan, Long> {
}
