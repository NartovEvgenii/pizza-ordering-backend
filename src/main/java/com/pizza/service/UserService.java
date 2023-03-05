package com.pizza.service;

import com.pizza.domains.Address;
import com.pizza.domains.MobUser;
import com.pizza.dto.AddressDTO;
import com.pizza.dto.MobUserDTORequest;
import com.pizza.dto.MobUserRequest;
import com.pizza.dto.MobUserDTOResponse;
import com.pizza.repository.AddressRepository;
import com.pizza.repository.UserRepository;
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

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public MobUserDTOResponse login(MobUserRequest user) throws Exception {
        MobUser mobUser = userRepository.findByEmail(user.getEmail());
        if(bcryptEncoder.matches(user.getPassword(), mobUser.getPassword())){
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobUser.getEmail(), user.getPassword()));
            } catch (DisabledException | BadCredentialsException e) {
                throw new BadCredentialsException("There is no user with this password or login.");
            }
            UserDetails userDetails = loadUserByUsername(mobUser.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);
            return mapModUserToUserResponse(mobUser, token);
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

    public MobUserDTOResponse register(MobUserDTORequest user) {
        Address findedaddress = user.getAddressDTO().getIdAddress() != null ? addressRepository
                .findById(user.getAddressDTO().getIdAddress())
                .orElse(null) : null;
        Address address =  findedaddress != null ? findedaddress
                            : addressRepository.save(new Address(user.getAddressDTO().getTitle()));
        MobUser mobUser = mapDTOUserRequestToMobUser(user);
        mobUser.setGeneralAddress(address);
        mobUser = userRepository.save(mobUser);
        String token = jwtTokenUtil.generateToken(new User(mobUser.getEmail(), mobUser.getPassword(), new ArrayList<>()));
        return mapModUserToUserResponse(mobUser, token);
    }

    private MobUserDTOResponse mapModUserToUserResponse(MobUser user, String token){
        MobUserDTOResponse mobUserDTOResponse = new MobUserDTOResponse();
        mobUserDTOResponse.setIdUser(user.getIdUser());
        mobUserDTOResponse.setEmail(user.getEmail());
        mobUserDTOResponse.setName(user.getName());
        mobUserDTOResponse.setSurname(user.getSurname());
        mobUserDTOResponse.setToken(token);
        mobUserDTOResponse.setAddressDTO(new AddressDTO(user.getGeneralAddress()));
        return mobUserDTOResponse;
    }

    private MobUser mapDTOUserRequestToMobUser(MobUserDTORequest mobUserDTORequest){
        MobUser mobUser = new MobUser();
        mobUser.setEmail(mobUserDTORequest.getEmail());
        mobUser.setName(mobUserDTORequest.getName());
        mobUser.setSurname(mobUserDTORequest.getSurname());
        mobUser.setPassword(bcryptEncoder.encode(mobUserDTORequest.getPassword()));
        return mobUser;
    }



}
