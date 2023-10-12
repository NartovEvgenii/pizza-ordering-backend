package com.pizza.dto;

import lombok.Data;

@Data
public class PizzaDTO {

    private Long idPizza;

    private String title;

    private String imagePath;

    private String description;

    private Double price;
}
