package com.pizza.service;

import com.pizza.domains.OrderState;

public interface OrderStateService {

    OrderState getOrderStateByIdentifier(String identifier);
}
