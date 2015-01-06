/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.presenter;

import org.djabry.platform.vaadin.account.AccountEvent;
import org.djabry.platform.vaadin.view.SideBarView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.vaadin.spring.navigator.VaadinPresenter;
import org.vaadin.spring.security.Security;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */

@VaadinPresenter(viewName = SideBarView.VIEW_NAME)
public class SideBarPresenter extends SamplePresenter<SideBarView> {

    @Autowired
    private Security security;

    @PostConstruct
    public void initView() {
        Authentication authentication = security.getAuthentication();
        boolean authenticated = false;
        if (authentication != null) {
            authenticated = authentication.isAuthenticated();
        }

        //getView().setSidebarVisible(authenticated);
        getView().setSidebarVisible(false);
    }


    //@EventBusListenerMethod(filter=AccountEventFilter.class)
    public void doOnAccountEvent(AccountEvent event) {
        super.doOnAccountEvent(event);
        getView().setSidebarVisible(event.isAuthenticated());

    }

}
