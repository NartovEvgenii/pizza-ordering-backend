package com.pizza.mapper;

import com.pizza.domains.MobUser;
import com.pizza.dto.MobUserDTO;
import com.pizza.dto.MobUserDTORequest;
import com.pizza.dto.MobUserDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {AddressMapper.class}
)
public interface MobUserMapper {

    @Mapping(source = "mobUser.generalAddress", target = "addressDTO")
    @Mapping(source = "mobUser.addresses", target = "addressDTOs")
    MobUserDTO mobUserToDto(MobUser mobUser);
    @Mapping(source = "mobUser.generalAddress", target = "addressDTO")
    @Mapping(source = "mobUser.addresses", target = "addressDTOs")
    MobUserDTOResponse mapModUserToUserResponse(MobUser mobUser, String token);

    @Mapping(source = "password", target = "password")
    MobUser mapDTOUserRequestToMobUser(MobUserDTORequest mobUserDTORequest,String password);
}
