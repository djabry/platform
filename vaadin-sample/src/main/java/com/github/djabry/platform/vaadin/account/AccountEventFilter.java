/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.djabry.platform.vaadin.account;

import org.vaadin.spring.events.EventBusListenerMethodFilter;

/**
 * @author djabry
 */
public class AccountEventFilter implements EventBusListenerMethodFilter {

    public boolean filter(Object payload) {
        return payload instanceof AccountEvent;
    }

}
