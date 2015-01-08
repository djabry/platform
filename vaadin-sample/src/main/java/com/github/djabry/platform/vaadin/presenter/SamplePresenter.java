/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.djabry.platform.vaadin.presenter;


import com.github.djabry.platform.vaadin.account.AccountEvent;
import com.github.djabry.platform.vaadin.account.AccountEventFilter;
import com.github.djabry.platform.vaadin.view.SampleView;
import org.vaadin.spring.events.EventBusListenerMethod;
import org.vaadin.spring.navigator.Presenter;

/**
 * @param <C> The type of view presented
 * @author djabry
 */
public class SamplePresenter<C extends SampleView> extends Presenter<C> {

    @EventBusListenerMethod(filter = AccountEventFilter.class)
    protected void onAccountEvent(AccountEvent event) {
        this.doOnAccountEvent(event);

    }

    protected void doOnAccountEvent(AccountEvent event) {

        if (!event.isAuthenticated()) {
            getView().clear();
        }

    }

}
