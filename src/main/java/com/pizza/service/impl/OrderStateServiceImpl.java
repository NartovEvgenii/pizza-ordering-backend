package com.pizza.service.impl;

import com.pizza.domains.OrderState;
import com.pizza.repository.OrderStateRepository;
import com.pizza.service.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrderStateServiceImpl implements OrderStateService {
    @Autowired
    private OrderStateRepository orderStateRepository;
    @Cacheable(value = "orderState", key = "#identifier")
    public OrderState getOrderStateByIdentifier(String identifier){
        return orderStateRepository.findByIdentifier(identifier)
                .orElseThrow();
    }
}
