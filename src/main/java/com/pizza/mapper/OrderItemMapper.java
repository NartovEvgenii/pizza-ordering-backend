package com.pizza.mapper;

import com.pizza.domains.OrderItem;
import com.pizza.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderItemMapper {

    @Mapping(source = "orderItem.pizza.idPizza", target = "idPizza")
    OrderItemDTO mapOrderItemToDTO(OrderItem orderItem);
}
