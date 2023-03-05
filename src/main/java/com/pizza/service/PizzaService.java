package com.pizza.service;

import com.pizza.domains.Pizza;
import com.pizza.dto.PizzaDto;
import com.pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public List<PizzaDto> getAllPizzas(){
        return pizzaRepository.findAll().stream()
                .map(this::mapPizzaToPizzaDTO)
                .collect(Collectors.toList());
    }

    private PizzaDto mapPizzaToPizzaDTO(Pizza pizza){
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setIdPizza(pizza.getIdPizza());
        pizzaDto.setTitle(pizza.getTitle());
        pizzaDto.setImagePath(pizza.getImagePath());
        pizzaDto.setDescription(pizza.getDescription());
        pizzaDto.setPrice(pizza.getPrice());
        return pizzaDto;
    }
}
