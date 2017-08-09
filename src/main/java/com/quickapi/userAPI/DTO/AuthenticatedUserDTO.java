/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickapi.userAPI.DTO;

import lombok.Data;


@Data
public class AuthenticatedUserDTO {

    private Long id;
    private String tokenSeries;

    public AuthenticatedUserDTO(Long id, String tokenSeries) {
        this.id = id;
        this.tokenSeries = tokenSeries;
    }

    public AuthenticatedUserDTO() {

    }

}
