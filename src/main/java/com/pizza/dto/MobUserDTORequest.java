package com.pizza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MobUserDTORequest extends MobUserRequest{

    private String name;

    private String surname;
    @JsonProperty("address")
    private AddressDTO addressDTO;

}
