/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.ui;


import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.djabry.platform.vaadin.presenter.Action;
import org.djabry.platform.vaadin.presenter.BannerPresenter;
import org.djabry.platform.vaadin.presenter.SideBarPresenter;
import org.djabry.platform.vaadin.view.BannerView;
import org.djabry.platform.vaadin.view.LoginView;
import org.djabry.platform.vaadin.view.SideBarView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.SpringViewProvider;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */

@VaadinUI
@Theme("valo")
@SuppressWarnings("serial")
@PreserveOnRefresh
@Title("Test Server")
//@Push
public class MainUI extends UI {

    @Autowired
    SpringViewProvider vP;

    @Autowired
    EventBus eventBus;

    @Autowired
    private BannerPresenter bannerPresenter;

    @Autowired
    private SideBarPresenter sideBarPresenter;

    private VerticalLayout body;

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setSizeFull();

        setContent(rootLayout);

        BannerView banner = bannerPresenter.getView();
        rootLayout.addComponent(banner);


        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();


        body = new VerticalLayout();
        body.setSizeFull();

        Navigator navigator = new Navigator(this, body);
        navigator.addProvider(vP);


        this.setNavigator(navigator);
        SideBarView sidebarView = sideBarPresenter.getView();

        mainLayout.addComponent(sidebarView);
        mainLayout.addComponent(body);

        rootLayout.addComponent(mainLayout);
        rootLayout.setExpandRatio(mainLayout, 10);

        sidebarView.setWidth(150, Unit.PIXELS);
        mainLayout.setExpandRatio(body, 10);
        //rootLayout.setSplitPosition(150, Unit.PIXELS);
        navigator.navigateTo(LoginView.VIEW_NAME);

        eventBus.publish(EventScope.SESSION, this, Action.START);
    }


    @PostConstruct
    public void initView() {


    }

}
