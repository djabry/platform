/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.presenter;


import org.djabry.platform.service.security.annotations.ReadOwn;
import org.djabry.platform.vaadin.view.HomeView;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * @author djabry
 */
@VaadinPresenter(viewName = HomeView.VIEW_NAME)
@SuppressWarnings("serial")
@ReadOwn
public class HomePresenter extends SamplePresenter<HomeView> {

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {

//        
//        getView().setBanner(banner.getView());
//        getView().setHeader(header.getView());
//        getView().setBody(body.getView());
//        getView().setFooter((Component) getViewProvider().getView(FooterView.NAME));
    }

}
