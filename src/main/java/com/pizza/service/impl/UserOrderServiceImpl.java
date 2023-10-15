package com.pizza.service.impl;

import com.pizza.domains.OrderItem;
import com.pizza.domains.OrderState;
import com.pizza.domains.UserOrder;
import com.pizza.dto.OrderItemDTO;
import com.pizza.dto.UserOrderDTO;
import com.pizza.dto.UserOrderDTORequest;
import com.pizza.mapper.OrderItemMapper;
import com.pizza.mapper.UserOrderMapper;
import com.pizza.repository.AddressRepository;
import com.pizza.repository.PizzaRepository;
import com.pizza.repository.UserOrderRepository;
import com.pizza.repository.UserRepository;
import com.pizza.service.OrderStateService;
import com.pizza.service.UserOrderService;
import com.pizza.utils.ServiceConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.pizza.utils.ServiceConstant.PROCESSED_IDENT;

@Service
@RequiredArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final UserOrderRepository userOrderRepository;
    private final PizzaRepository pizzaRepository;
    private final UserOrderMapper userOrderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderStateService orderStateService;


    @Transactional
    public UserOrderDTO addUserOrder(UserOrderDTORequest userOrderDTORequest) {
        UserOrder userOrder = createNewUserOrder(userOrderDTORequest);
        userOrder = userOrderRepository.save(userOrder);
        return userOrderMapper.mapUserOrderToDTO(userOrder);
    }

    @Override
    public List<UserOrderDTO> getUserOrdersByUser(Long idUser) {
        List<UserOrder> userOrderList = userOrderRepository.findByMobUserId(idUser);
        return userOrderMapper.mapUserOrdersToDTOs(userOrderList);
    }

    @Override
    public Set<UserOrder> getUserOrdersByOrderStateIdent(String orderStateIdent) {
        OrderState orderState = orderStateService.getOrderStateByIdentifier(orderStateIdent);
        Set<UserOrder> userOrderList = userOrderRepository.findByOrderState(orderState.getIdOrderState());
        return userOrderList;
    }

    private UserOrder createNewUserOrder(UserOrderDTORequest userOrderDTORequest) {
        UserOrder userOrder = new UserOrder();
        userOrder.setFullPrice(userOrderDTORequest.getFullPrice());
        userOrder.setAddress(addressRepository.getOne(userOrderDTORequest.getIdAddress()));
        userOrder.setMobUser(userRepository.getOne(userOrderDTORequest.getIdUser()));
        String identifier = userOrderDTORequest.getOrderState() != null
                ? userOrderDTORequest.getOrderState()
                : ServiceConstant.START_ORDER_STATE;
        userOrder.setOrderState(orderStateService.getOrderStateByIdentifier(identifier));
        userOrder.setOrderItems(createOrderItems(userOrderDTORequest.getOrderItemDTOS(), userOrder));
        return userOrder;
    }

    private Set<OrderItem> createOrderItems(List<OrderItemDTO> orderItemDTOS, UserOrder userOrder){
        return orderItemDTOS.stream()
                .map(orderItemDTO -> {
                    OrderItem orderItem = orderItemMapper.mapNewDTOToOrderItem(orderItemDTO);
                    orderItem.setPizza(pizzaRepository.getOne(orderItemDTO.getIdPizza()));
                    orderItem.setOrder(userOrder);
                    return orderItem;
                })
                .collect(Collectors.toSet());
    }

}
