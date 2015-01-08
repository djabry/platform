/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.djabry.platform.vaadin.account;

import lombok.Data;

import java.io.Serializable;

/**
 * @author djabry
 */

@Data
public class AccountEvent implements Serializable {
    //private Authentication authentication;

    private boolean authenticated = false;

}
