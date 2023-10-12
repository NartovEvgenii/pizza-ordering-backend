package com.pizza.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserOrderDTORequest {

    private Double fullPrice;

    private String orderState;

    private String paymentType;

    private Long idAddress;

    private Long idUser;

    private List<OrderItemDTO> orderItemDTOS;
}
