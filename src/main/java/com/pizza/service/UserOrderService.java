package com.pizza.service;

import com.pizza.dto.UserOrderDTO;
import com.pizza.dto.UserOrderDTORequest;

import java.util.List;

public interface UserOrderService {

    UserOrderDTO addUserOrder(UserOrderDTORequest userOrderDTORequest);

    List<UserOrderDTO> getUserOrdersByUser(Long idUser);
}
