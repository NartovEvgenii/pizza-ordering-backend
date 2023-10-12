package com.pizza.mapper;

import com.pizza.domains.UserOrder;
import com.pizza.dto.UserOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {AddressMapper.class, MobUserMapper.class, OrderItemMapper.class}
)
public interface UserOrderMapper {
    @Mapping(source = "userOrder.orderState.title", target = "orderState")
    @Mapping(source = "userOrder.paymentType.title", target = "paymentType")
    UserOrderDTO mapUserOrderToDTO(UserOrder userOrder);
}
