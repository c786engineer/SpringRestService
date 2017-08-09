package com.quickapi.userAPI.controllers;

import com.quickapi.userAPI.DTO.LoginDTO;
import com.quickapi.userAPI.DTO.AuthenticatedUserDTO;
import com.quickapi.userAPI.entities.UserEntity;
import com.quickapi.userAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    
    @RequestMapping(value = "/user/add", method = RequestMethod.PUT)
    public UserEntity add(@RequestBody UserEntity user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<UserEntity> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public AuthenticatedUserDTO login(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }

    @RequestMapping(value = "/user/logout/{userId}", method = RequestMethod.POST)
    public AuthenticatedUserDTO logout(@PathVariable Long userId) {
        System.out.println("userId="+userId);
        return userService.logout(userId);
    }

}
