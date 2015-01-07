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

        @SideBarSection(id = Sections.GENERAL, caption = "General"),
        @SideBarSection(id = Sections.ACCOUNT, caption = "Account")
})
@Component
@SuppressWarnings("serial")
//@CreateAny
public class Sections {

    public static final String GENERAL = "general";
    public static final String ACCOUNT = "account";


}
