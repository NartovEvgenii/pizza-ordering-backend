package com.pizza.service.impl;

import com.pizza.dto.PizzaDTO;
import com.pizza.mapper.PizzaMapper;
import com.pizza.repository.PizzaRepository;
import com.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaMapper pizzaMapper;

    public List<PizzaDTO> getAllPizzas(){
        return pizzaRepository.findAll().stream()
                .map(pizzaMapper::mapPizzaToPizzaDTO)
                .collect(Collectors.toList());
    }

}
