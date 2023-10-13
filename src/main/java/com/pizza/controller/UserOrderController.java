package com.pizza.controller;

import com.pizza.dto.UserOrderDTO;
import com.pizza.dto.UserOrderDTORequest;
import com.pizza.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userOrder")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    @PostMapping()
    public UserOrderDTO addUserOrder(@RequestBody UserOrderDTORequest userOrderDTORequest) {
        return userOrderService.addUserOrder(userOrderDTORequest);
    }

    @GetMapping("/{idUser}")
    public List<UserOrderDTO> getUserOrdersByUser(@PathVariable Long idUser) {
        return userOrderService.getUserOrdersByUser(idUser);
    }
}
