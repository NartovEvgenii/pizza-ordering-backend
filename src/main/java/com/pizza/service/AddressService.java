package com.pizza.service;

import com.pizza.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    List<AddressDTO> getAllAddresses();

    AddressDTO addAddress(AddressDTO addressDTO);

    List<AddressDTO> findAddressByTitle(String title);
}
