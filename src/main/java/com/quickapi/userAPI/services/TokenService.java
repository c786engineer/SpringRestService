/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickapi.userAPI.services;

import com.quickapi.userAPI.Application;
import static com.quickapi.userAPI.Application.TOKEN_CHECK_DURATION_IN_MILISECONDS;
import static com.quickapi.userAPI.Application.TOKEN_DURATION_IN_SECONDS;
import com.quickapi.userAPI.entities.TokenEntity;
import com.quickapi.userAPI.entities.UserEntity;
import com.quickapi.userAPI.repositories.TokenRepository;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jalal-sordo
 */
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private SessionIdentifierGenerator sessionIdentifierGenerator;

    public Iterable<TokenEntity> getAllTokens() {
        return tokenRepository.findAll();
    }

    public TokenEntity createToken(UserEntity aUser) {
        TokenEntity token = new TokenEntity(aUser);
        token.setSeries(sessionIdentifierGenerator.nextSessionId());
        return tokenRepository.save(token);
    }

    public boolean isValidToken(String tokenSeries) {
        TokenEntity token = tokenRepository.findByTokenSeries(tokenSeries);
        if (token != null) {
            DateTime parsedTime = new DateTime(token.getDateOfCreation());
            Duration tokenLifeSpan = new Duration(DateTime.now(), parsedTime);
            if (Math.abs(tokenLifeSpan.getStandardSeconds()) <= TOKEN_DURATION_IN_SECONDS) {
                refreshToken(token);
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void checkTokensForExpiration() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("CHECKING TOKENS EVERY " + TOKEN_DURATION_IN_SECONDS);
                Iterable<TokenEntity> AllTokens = tokenRepository.findAll();
                int tokensExpired = 0;
                for (TokenEntity token : AllTokens) {
                    DateTime parsedTime = new DateTime(token.getDateOfCreation());
                    Duration tokenLifeSpan = new Duration(DateTime.now(), parsedTime);
                    if (Math.abs(tokenLifeSpan.getStandardSeconds()) <= TOKEN_DURATION_IN_SECONDS) {
                        tokenRepository.delete(token.getId());
                        tokensExpired++;
                    }
                }
                System.out.println("EXPIRED TOKENS " + tokensExpired);
            }
        }, 0, TOKEN_CHECK_DURATION_IN_MILISECONDS);
    }

    private void refreshToken(TokenEntity token) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        token.setDateOfCreation(currentTimestamp);
        tokenRepository.save(token);
    }

    public TokenEntity getUserToken(UserEntity aUser) {
        if (aUser != null) {
            return tokenRepository.getUserToken(aUser.getId());
        }
        return null;
    }

}
