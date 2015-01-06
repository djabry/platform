/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.account;


import com.vaadin.ui.UI;
import org.djabry.platform.vaadin.view.HomeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.security.Security;

/**
 * @author djabry
 */

@VaadinComponent
@UIScope
public class LoginAction {

    @Autowired
    private Security security;

    @Autowired
    private EventBus eventBus;

    public void login(String username, String password) {
        security.login(username, password);

        AccountEvent accountEvent = new AccountEvent();

        Authentication authentication = security.getAuthentication();
        if (authentication != null) {
            accountEvent.setAuthenticated(authentication.isAuthenticated());
        }

        eventBus.publish(EventScope.SESSION, this, accountEvent);

        UI.getCurrent().getNavigator().navigateTo(HomeView.VIEW_NAME);


    }


}
