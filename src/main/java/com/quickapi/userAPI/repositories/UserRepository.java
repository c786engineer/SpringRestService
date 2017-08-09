package com.quickapi.userAPI.repositories;

import com.quickapi.userAPI.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT u FROM users u WHERE u.username=:username and u.password=:password")
    public UserEntity loginUser(@Param("username") String username,
            @Param("password") String password);

}
