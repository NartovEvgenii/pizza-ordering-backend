package com.pizza.controller;

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
    public MobUserDTOResponse login(@RequestBody MobUserRequest user) throws Exception {
        return userService.login(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public MobUserDTOResponse saveUser(@RequestBody MobUserDTORequest user) throws Exception {
        return userService.register(user);
    }

}
