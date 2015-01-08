/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.djabry.platform.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author djabry
 */
@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.github.djabry")
public class TestConfig {


}
