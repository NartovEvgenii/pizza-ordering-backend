package com.pizza.service.impl;

import com.pizza.domains.UserOrder;
import com.pizza.dto.UserOrderDTO;
import com.pizza.dto.UserOrderDTORequest;
import com.pizza.mapper.UserOrderMapper;
import com.pizza.repository.AddressRepository;
import com.pizza.repository.UserOrderRepository;
import com.pizza.repository.UserRepository;
import com.pizza.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private UserOrderMapper userOrderMapper;
    @Transactional
    public UserOrderDTO addUserOrder(UserOrderDTORequest userOrderDTORequest){
        UserOrder userOrder = createNewUserOrder(userOrderDTORequest);
        userOrder = userOrderRepository.save(userOrder);
        return userOrderMapper.mapUserOrderToDTO(userOrder);
    }

    private UserOrder createNewUserOrder(UserOrderDTORequest userOrderDTORequest){
        UserOrder userOrder = new UserOrder();
        userOrder.setFullPrice(userOrderDTORequest.getFullPrice());
        userOrder.setAddress(addressRepository.getOne(userOrderDTORequest.getIdAddress()));
        userOrder.setMobUser(userRepository.getOne(userOrderDTORequest.getIdUser()));
        return userOrder;
    }

}
