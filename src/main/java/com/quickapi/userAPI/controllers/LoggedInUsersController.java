package com.quickapi.userAPI.controllers;

import com.quickapi.userAPI.entities.LoggedInUser;
import com.quickapi.userAPI.services.LoggedInUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggedInUsersController {

    @Autowired
    private LoggedInUserService loggedInUsersService;

    @RequestMapping(value = "/logged-in-users", method = RequestMethod.GET)
    public Iterable<LoggedInUser> getAllLoggedInUsers(@RequestParam("token") String tokenSeries) {
        return loggedInUsersService.getAllLoggedInUsers(tokenSeries);
    }

    @RequestMapping(value = "/logged-in-users-log-5min", method = RequestMethod.GET)
    public Iterable<LoggedInUser> getAllLoggedInUsersInLast5Min(@RequestParam("token") String tokenSeries) {
        return loggedInUsersService.getAllLoggedInUsersInLast5Min(tokenSeries);
    }

}
