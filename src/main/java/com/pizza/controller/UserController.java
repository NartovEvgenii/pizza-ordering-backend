package com.pizza.controller;

import com.pizza.dto.MobUserDTO;
import com.pizza.dto.MobUserDTORequest;
import com.pizza.dto.MobUserRequest;
import com.pizza.dto.MobUserDTOResponse;
import com.pizza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MobUserDTOResponse login(@RequestBody MobUserRequest user) {
        return userService.login(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public MobUserDTOResponse saveUser(@RequestBody MobUserDTORequest user) {
        return userService.register(user);
    }

    @GetMapping(value = "/{idUser}")
    public MobUserDTO getUserById(@PathVariable Long idUser) {
        return userService.getUserById(idUser);
    }

}
