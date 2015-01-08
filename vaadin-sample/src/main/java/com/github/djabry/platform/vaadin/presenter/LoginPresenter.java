/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.djabry.platform.vaadin.presenter;


import com.github.djabry.platform.vaadin.view.LoginView;
import org.vaadin.spring.navigator.VaadinPresenter;

/**
 * @author djabry
 */
@VaadinPresenter(viewName = LoginView.VIEW_NAME)
@SuppressWarnings("serial")
public class LoginPresenter extends SamplePresenter<LoginView> {

}
