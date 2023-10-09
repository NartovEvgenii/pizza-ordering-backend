package com.pizza.mapper;

import com.pizza.domains.Address;
import com.pizza.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface AddressMapper {

    AddressDTO mapAddressToAddressDTO(Address address);

    Address mapAddressDTOToAddress(AddressDTO addressDTO);
}
