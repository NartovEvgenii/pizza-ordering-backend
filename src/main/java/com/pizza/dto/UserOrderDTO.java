package com.pizza.dto;

import com.pizza.domains.*;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
public class UserOrderDTO {

    private Long idOrder;

    private Double fullPrice;

    private String orderState;

    private String paymentType;

    private AddressDTO address;

    private MobUserDTO mobUser;

    private List<OrderItemDTO> orderItems = new ArrayList<>();
}
