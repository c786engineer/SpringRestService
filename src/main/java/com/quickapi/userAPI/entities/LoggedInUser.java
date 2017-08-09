/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickapi.userAPI.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author jalal-sordo
 */
@Data
@Entity(name = "loggedInUsers")
public class LoggedInUser {

    public LoggedInUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Timestamp dateOfCreation;

    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity refUser;

    public LoggedInUser(UserEntity refUser) {
        this.refUser = refUser;

        Calendar calendar = Calendar.getInstance();

        java.util.Date now = calendar.getTime();

        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        dateOfCreation = currentTimestamp;
    }

}
