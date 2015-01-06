package org.djabry.platform.vaadin.config;

import lombok.extern.java.Log;
import org.djabry.platform.domain.api.SecurityToken;
import org.djabry.platform.service.api.AuthenticationService;
import org.djabry.platform.service.profile.Dev;
import org.djabry.platform.service.security.DefaultSignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by djabry on 06/01/15.
 */
@Component
@Dev
@Log
public class StartupUsersSignup {

    @Autowired
    private AuthenticationService authenticationService;


    @PostConstruct
    public void createDummyUsers() {

        DefaultSignUpRequest request = new DefaultSignUpRequest();
        request.setEmail("john@example.com");
        request.setUsername("johnexample");
        request.setPassword("password");
        SecurityToken securityToken = authenticationService.signUp(request);
        if (securityToken == null) {
            log.severe("Failed to sign up test users");
        } else {
            log.info("Successfully signed up as: username = " + securityToken.getUser().getUsername());
        }


    }
}
