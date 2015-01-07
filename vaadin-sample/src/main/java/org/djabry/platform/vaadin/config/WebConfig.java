/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.djabry.platform.vaadin.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.vaadin.spring.security.EnableVaadinSecurity;
import org.vaadin.spring.stuff.sidebar.EnableSideBar;

/**
 * @author djabry
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.djabry"})
@EnableAsync
@EnableScheduling
@EnableSideBar
@EnableVaadinSecurity
@EnableAutoConfiguration
//@Import(MethodSecurityConfig.class)
//@Order(1)
public class WebConfig extends WebMvcConfigurerAdapter {


}
