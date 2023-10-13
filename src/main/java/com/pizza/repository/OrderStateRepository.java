package com.pizza.repository;

import com.pizza.domains.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, Long> {

    Optional<OrderState> findByIdentifier(String identifier);
}
