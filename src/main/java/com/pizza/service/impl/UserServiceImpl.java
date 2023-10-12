package com.pizza.service.impl;

import com.pizza.domains.Address;
import com.pizza.domains.MobUser;
import com.pizza.dto.*;
import com.pizza.mapper.MobUserMapper;
import com.pizza.repository.AddressRepository;
import com.pizza.repository.UserRepository;
import com.pizza.service.UserService;
import com.pizza.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MobUserMapper mobUserMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public MobUserDTOResponse login(MobUserRequest user) {
        MobUser mobUser = userRepository.findByEmail(user.getEmail());
        if (bcryptEncoder.matches(user.getPassword(), mobUser.getPassword())) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobUser.getEmail(), user.getPassword()));
            } catch (DisabledException | BadCredentialsException e) {
                throw new BadCredentialsException("There is no user with this password or login.");
            }
            UserDetails userDetails = loadUserByUsername(mobUser.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);
            return mobUserMapper.mapModUserToUserResponse(mobUser, token);
        } else {
            throw new BadCredentialsException("There is no user with this password or login in database.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MobUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Transactional
    public MobUserDTOResponse register(MobUserDTORequest user) {
        Address findedaddress = user.getAddressDTO().getIdAddress() != null ? addressRepository
                .findById(user.getAddressDTO().getIdAddress())
                .orElse(null) : null;
        Address address = findedaddress != null
                ? findedaddress
                : addressRepository.save(new Address(user.getAddressDTO().getTitle()));
        MobUser mobUser = mobUserMapper.mapDTOUserRequestToMobUser(user, bcryptEncoder.encode(user.getPassword()));
        mobUser.setGeneralAddress(address);
        mobUser.getAddresses().add(address);
        mobUser = userRepository.save(mobUser);
        String token = jwtTokenUtil.generateToken(new User(mobUser.getEmail(), mobUser.getPassword(), new ArrayList<>()));
        return mobUserMapper.mapModUserToUserResponse(mobUser, token);
    }

    public MobUserDTO getUserById(Long idUser) {
        MobUser mobUser = userRepository.findById(idUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + idUser));
        return mobUserMapper.mobUserToDto(mobUser);
    }

}
