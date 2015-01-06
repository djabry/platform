/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.view;


import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import org.djabry.platform.vaadin.account.LogoutAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.navigator.VaadinView;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */

@UIScope
@VaadinView(name = BannerView.VIEW_NAME)
public class BannerView extends HorizontalLayout implements SampleView {

    public static final String VIEW_NAME = "banner";
    private Component title;
    private Button logoutButton;
    @Autowired
    private LogoutAction logoutAction;

    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    public boolean isShowLogout() {
        return this.logoutButton.isVisible();
    }

    /**
     * @param tf The boolean to switch between login (true) and logout (false)
     */
    public void setShowLogout(boolean tf) {
        this.logoutButton.setVisible(tf);
    }

    @PostConstruct
    public void initView() {
        //loginForm = this.buildLoginForm();

        title = buildTitle();
        logoutButton = this.buildLogoutButton();

        this.addComponent(title);

        //this.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        this.addComponent(title);


        this.addComponent(logoutButton);


        this.setComponentAlignment(logoutButton, Alignment.BOTTOM_RIGHT);
        //title.setHeight(70, Unit.PIXELS);
        this.setExpandRatio(title, 1);

        //this.addStyleName(ValoTheme.LAYOUT_WELL);
        this.addStyleName(ValoTheme.MENU_TITLE);
        //this.setSpacing(true);
        //this.setMargin(true);

        this.setWidth("100%");


    }

    private Component buildTitle() {

        Label t = new Label("CrowdEmotion Admin Dashboard");
        t.addStyleName(ValoTheme.LABEL_H1);

        return t;
    }

    private Button buildLogoutButton() {
        Button logout = new Button("Sign out");
        logout.setIcon(FontAwesome.SIGN_OUT);
        logout.addStyleName(ValoTheme.BUTTON_PRIMARY);
        logout.addClickListener(new ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                logoutAction.run();
            }
        });
        logout.setVisible(false);
        return logout;
    }

    public void clear() {

    }


}
