/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickapi.userAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author jalal-sordo
 */
@Data
@Entity(name = "tokens")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String series;
    private Timestamp dateOfCreation;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity refUser;

    public TokenEntity() {
    }

    public TokenEntity(UserEntity refUser) {
        Calendar calendar = Calendar.getInstance();

        Date now = calendar.getTime();

        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        dateOfCreation = currentTimestamp;
        this.refUser = refUser;
    }

}
