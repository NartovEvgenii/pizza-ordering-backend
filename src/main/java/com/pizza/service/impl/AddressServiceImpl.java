package com.pizza.service.impl;

import com.pizza.domains.Address;
import com.pizza.dto.AddressDTO;
import com.pizza.mapper.AddressMapper;
import com.pizza.repository.AddressRepository;
import com.pizza.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    public List<AddressDTO> getAllAddresses(){
        return addressRepository.findAll().stream()
                .map(addressMapper::mapAddressToAddressDTO)
                .collect(Collectors.toList());
    }

    public AddressDTO addAddress(AddressDTO addressDTO){
        Address address = addressMapper.mapAddressDTOToAddress(addressDTO);
        return addressMapper.mapAddressToAddressDTO(addressRepository.save(address));
    }

    public List<AddressDTO> findAddressByTitle(String title){
        return addressRepository.findAddressByTitle(title).stream()
                .map(addressMapper::mapAddressToAddressDTO)
                .collect(Collectors.toList());
    }

}
