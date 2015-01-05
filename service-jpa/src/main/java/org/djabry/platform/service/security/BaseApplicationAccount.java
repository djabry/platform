package org.djabry.platform.service.security;

import org.djabry.platform.service.api.AuthenticationService;
import org.djabry.platform.service.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by djabry on 05/01/15.
 */
public abstract class BaseApplicationAccount implements ApplicationAccount {
    @Autowired
    protected AuthenticationService authenticationService;

    @Autowired
    protected SecurityConfig securityConfig;

    @Override
    public void signIn() {
        authenticationService.login(securityConfig.getApplicationName(), securityConfig.getApplicationPassword());

    }
}
