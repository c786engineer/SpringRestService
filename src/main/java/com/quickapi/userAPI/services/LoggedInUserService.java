package com.quickapi.userAPI.services;

import com.quickapi.userAPI.Application;
import com.quickapi.userAPI.entities.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quickapi.userAPI.repositories.LoggedInUserRepository;
import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.Duration;

@Service
public class LoggedInUserService {

    @Autowired
    private LoggedInUserRepository loggedInUsersRepository;

    @Autowired
    private TokenService tokenService;

    private Iterable<LoggedInUser> getAll() {
        return loggedInUsersRepository.findAll();
    }

    public void delete(Long userId) {
        loggedInUsersRepository.delete(userId);

    }

    public Iterable<LoggedInUser> getAllLoggedInUsers(String tokenSeries) {
        if (tokenService.isValidToken(tokenSeries)) {

            return getAll();
        }
        return null;
    }

    public Iterable<LoggedInUser> getAllLoggedInUsersInLast5Min(String tokenSeries) {
       if (tokenService.isValidToken(tokenSeries)) {

            return getAllLoggedInUsersInLast5Min();
        }
        return null;
    }

    private Iterable<LoggedInUser> getAllLoggedInUsersInLast5Min() {
        ArrayList<LoggedInUser> logs5Min = new ArrayList<>();
        Iterable<LoggedInUser> logs = getAll();
        for (LoggedInUser logUser : logs) {
            DateTime parsedTime = new DateTime(logUser.getDateOfCreation());
            Duration userLogSpan = new Duration(DateTime.now(),parsedTime);
            if(Math.abs(userLogSpan.getStandardMinutes())>=Application.LOGGEDUSER_5Minutes_INTERVAL){
                logs5Min.add(logUser);
            }
        }
        return logs5Min;
    }
}
