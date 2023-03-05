package com.pizza.controller;

import com.pizza.dto.AddressDTO;
import com.pizza.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PostMapping
    public AddressDTO AddAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }
    @GetMapping("/search")
    public List<AddressDTO> findAddressByTitle(@RequestParam String title){
        return addressService.findAddressByTitle(title);
    }
}
