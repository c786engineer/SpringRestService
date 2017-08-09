/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickapi.userAPI.repositories;

import com.quickapi.userAPI.entities.LoggedInUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jalal-sordo
 */
public interface LoggedInUserRepository extends CrudRepository<LoggedInUser, Long> {

    
    @Query("SELECT l FROM loggedInUsers l WHERE l.refUser.id=:user_id")
    public LoggedInUser findByUserID(@Param("user_id") Long user_id);

  

}
