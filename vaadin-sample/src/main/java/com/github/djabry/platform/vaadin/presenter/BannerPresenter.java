/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.djabry.platform.vaadin.presenter;


import com.github.djabry.platform.vaadin.account.AccountEvent;
import com.github.djabry.platform.vaadin.view.BannerView;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * @author djabry
 */

@VaadinPresenter(viewName = BannerView.VIEW_NAME)
@SuppressWarnings("serial")
public class BannerPresenter extends SamplePresenter<BannerView> {


    @Override
    protected void doOnAccountEvent(AccountEvent event) {
        super.doOnAccountEvent(event);
        getView().setShowLogout(event.isAuthenticated());
    }


}
