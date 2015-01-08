/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.djabry.platform.vaadin.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.vaadin.spring.boot.VaadinAutoConfiguration;
import org.vaadin.spring.config.VaadinConfiguration;

/**
 * @author djabry
 */
public class ApplicationInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.showBanner(false)
                .sources(WebConfig.class)
                        //.sources(WebSecurityConfig.class)
                .sources(VaadinAutoConfiguration.class, VaadinConfiguration.class);


    }
}
