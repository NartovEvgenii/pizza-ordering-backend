package com.pizza.controller;

import com.pizza.dto.UserOrderDTO;
import com.pizza.dto.UserOrderDTORequest;
import com.pizza.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userOrder")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @PostMapping(value = "/login")
    public UserOrderDTO addUserOrder(@RequestBody UserOrderDTORequest userOrderDTORequest) {
        return userOrderService.addUserOrder(userOrderDTORequest);
    }
}
