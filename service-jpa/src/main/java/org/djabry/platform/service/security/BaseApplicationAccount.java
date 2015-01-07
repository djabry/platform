package org.djabry.platform.service.security;

import lombok.extern.java.Log;
import org.djabry.platform.domain.api.SecurityToken;
import org.djabry.platform.service.api.SpringAuthenticationService;
import org.djabry.platform.service.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by djabry on 05/01/15.
 */
@Log
public abstract class BaseApplicationAccount implements ApplicationAccount {
    @Autowired
    protected SpringAuthenticationService springAuthenticationService;

    @Autowired
    protected SecurityConfig securityConfig;

    @Override
    public void signIn() {
        String username = securityConfig.getApplicationName();
        String password = securityConfig.getApplicationPassword();
        log.info("Attempting to log in as application, username = " + username + ", password = " + password);
        SecurityToken login = springAuthenticationService.login(username, password);

        if (login == null) {
            log.severe("Failed to log in as application");
        } else {
            log.info("Successfully logged in as application");
        }

    }
}
