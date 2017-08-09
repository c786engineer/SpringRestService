package com.quickapi.userAPI.services;

import com.quickapi.userAPI.entities.TokenEntity;
import com.quickapi.userAPI.DTO.AuthenticatedUserDTO;
import com.quickapi.userAPI.DTO.LoginDTO;
import com.quickapi.userAPI.entities.LoggedInUser;
import com.quickapi.userAPI.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quickapi.userAPI.repositories.UserRepository;
import com.quickapi.userAPI.repositories.LoggedInUserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoggedInUserRepository loggedUsersRepository;

    public Iterable<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity get(Long userId) {
        return userRepository.findOne(userId);
    }

    public UserEntity update(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity add(UserEntity user) {
        return userRepository.save(user);
    }

    public AuthenticatedUserDTO loginUser(LoginDTO loginDTO) {

        UserEntity aUser = userRepository.loginUser(loginDTO.getUsername(), loginDTO.getPassword());
        if (aUser != null) {
            TokenEntity token = tokenService.createToken(aUser);
            loggedUsersRepository.save(new LoggedInUser(aUser));
            return new AuthenticatedUserDTO(aUser.getId(), token.getSeries());
        }
        return null;

    }

    public AuthenticatedUserDTO logout(Long userId) {
        UserEntity aUser = userRepository.findOne(userId);
        if (aUser != null) {
            TokenEntity token = tokenService.getUserToken(aUser);
            LoggedInUser logUser = loggedUsersRepository.findByUserID(aUser.getId());
            loggedUsersRepository.delete(logUser.getId());
            return new AuthenticatedUserDTO(aUser.getId(), token.getSeries());
        }
        return null;

    }
}
