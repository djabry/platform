/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.account;

import com.vaadin.ui.UI;
import org.djabry.platform.vaadin.presenter.Sections;
import org.djabry.platform.vaadin.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.security.Security;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

/**
 * @author djabry
 */

@VaadinComponent
@UIScope
@SideBarItem(sectionId = Sections.ACCOUNT, caption = "Sign out")
//@FontAwesomeIcon(FontAwesome.SIGN_OUT)
public class LogoutAction implements Runnable {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private Security security;


    public void run() {
        security.logout();

        AccountEvent accountEvent = new AccountEvent();
        accountEvent.setAuthenticated(false);

        eventBus.publish(EventScope.SESSION, this, accountEvent);

        UI.getCurrent().getNavigator().navigateTo(LoginView.VIEW_NAME);
    }

}