package com.pizza.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class MobUserRequest {

    private String email;

    private String password;

}
