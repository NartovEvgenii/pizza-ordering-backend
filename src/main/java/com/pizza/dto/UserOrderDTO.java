package com.pizza.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserOrderDTO {

    private Long idOrder;

    private Double fullPrice;

    private String orderState;

    private String paymentType;

    private AddressDTO address;

    private Long idMobUser;

    private List<OrderItemDTO> orderItems = new ArrayList<>();
}
