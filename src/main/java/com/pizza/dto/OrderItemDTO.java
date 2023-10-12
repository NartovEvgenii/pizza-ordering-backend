package com.pizza.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long idOrderItem;
    private Double price;
    private Integer countItems;
    private Long idPizza;
}
