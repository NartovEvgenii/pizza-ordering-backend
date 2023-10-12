package com.pizza.service;

import com.pizza.domains.UserOrder;
import com.pizza.dto.UserOrderDTO;
import com.pizza.dto.UserOrderDTORequest;

public interface UserOrderService {

    UserOrderDTO addUserOrder(UserOrderDTORequest userOrderDTORequest);
}
