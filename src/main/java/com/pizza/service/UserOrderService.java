package com.pizza.service;

import com.pizza.domains.OrderState;
import com.pizza.domains.UserOrder;
import com.pizza.dto.UserOrderDTO;
import com.pizza.dto.UserOrderDTORequest;

import java.util.List;
import java.util.Set;

public interface UserOrderService {

    UserOrderDTO addUserOrder(UserOrderDTORequest userOrderDTORequest);

    List<UserOrderDTO> getUserOrdersByUser(Long idUser);

    Set<UserOrder> getUserOrdersByOrderStateIdent(String orderStateIdent);
}
