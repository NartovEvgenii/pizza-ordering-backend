package com.pizza.service;

import com.pizza.dto.MobUserDTO;
import com.pizza.dto.MobUserDTORequest;
import com.pizza.dto.MobUserDTOResponse;
import com.pizza.dto.MobUserRequest;

public interface UserService {

    MobUserDTOResponse login(MobUserRequest user);
    MobUserDTOResponse register(MobUserDTORequest user);
    MobUserDTO getUserById(Long idUser);
}
