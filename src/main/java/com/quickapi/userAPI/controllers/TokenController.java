package com.quickapi.userAPI.controllers;

import com.quickapi.userAPI.entities.TokenEntity;
import com.quickapi.userAPI.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public Iterable<TokenEntity> getAll() {
        return tokenService.getAllTokens();
    }

}
