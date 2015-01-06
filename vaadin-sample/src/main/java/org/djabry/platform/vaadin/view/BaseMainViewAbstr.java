/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.view;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.spring.navigator.VaadinView;

import javax.annotation.PostConstruct;

/**
 * @author djabry
 */
public abstract class BaseMainViewAbstr extends VerticalLayout implements SampleView {

    @PostConstruct
    protected void init() {
        MarginInfo marginInfo = new MarginInfo(true, true, true, true);
        this.setMargin(marginInfo);
        this.setSpacing(true);

        String titleString = this.getTitle();
        if (titleString != null) {
            Label title = new Label(getTitle());
            title.addStyleName(ValoTheme.LABEL_H2);
            this.addComponent(title);
        }

    }

    public String getTitle() {

        String title = this.getViewName();
        if (title.length() > 1) {
            title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
        }

        return title;
    }

    public String getViewName() {
        VaadinView annotation = this.getClass().getAnnotation(VaadinView.class);
        String name = null;
        if (annotation != null) {
            name = annotation.name();

        }

        return name;
    }


}
