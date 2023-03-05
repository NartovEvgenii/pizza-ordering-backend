package com.pizza.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PizzaDto {

    private Long idPizza;

    private String title;

    private String imagePath;

    private String description;

    private Double price;
}
