package com.pizza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MobUserDTOResponse {

    private Long idUser;

    private String email;

    private String name;

    private String surname;

    private String token;
    @JsonProperty("address")
    private AddressDTO addressDTO;


}
