package com.pizza.dto;

import com.pizza.domains.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class AddressDTO {

    private Long idAddress;

    private String title;

}
