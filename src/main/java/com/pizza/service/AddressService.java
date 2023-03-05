package com.pizza.service;

import com.pizza.domains.Address;
import com.pizza.dto.AddressDTO;
import com.pizza.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<AddressDTO> getAllAddresses(){
        return addressRepository.findAll().stream()
                .map(this::mapAddressToAddressDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO addAddress(AddressDTO addressDTO){
        Address address = mapAddressDTOToAddress(addressDTO);
        return mapAddressToAddressDTO(addressRepository.save(address));
    }

    public List<AddressDTO> findAddressByTitle(String title){
        return addressRepository.findAddressByTitle(title).stream()
                .map(this::mapAddressToAddressDTO)
                .collect(Collectors.toList());
    }

    private AddressDTO mapAddressToAddressDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setIdAddress(address.getIdAddress());
        addressDTO.setTitle(address.getTitle());
        return addressDTO;
    }

    private Address mapAddressDTOToAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setTitle(addressDTO.getTitle());
        return address;
    }
}
