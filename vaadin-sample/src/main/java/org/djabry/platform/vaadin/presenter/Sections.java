/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.presenter;

import org.springframework.stereotype.Component;
import org.vaadin.spring.stuff.sidebar.SideBarSection;
import org.vaadin.spring.stuff.sidebar.SideBarSections;

/**
 * @author djabry
 */
@SideBarSections({

        @SideBarSection(id = Sections.SETTINGS, caption = "Settings"),
        @SideBarSection(id = Sections.ACCOUNT, caption = "Account")
})
@Component
//@CreateAny
public class Sections {

    public static final String SETTINGS = "settings";
    public static final String ACCOUNT = "account";


}
