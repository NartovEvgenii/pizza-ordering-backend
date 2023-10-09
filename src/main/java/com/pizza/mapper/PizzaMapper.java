package com.pizza.mapper;

import com.pizza.domains.Pizza;
import com.pizza.dto.PizzaDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PizzaMapper {

    PizzaDto mapPizzaToPizzaDTO(Pizza pizza);
}
