package org.djabry.platform.service.security;

import org.djabry.platform.service.profile.Dev;
import org.springframework.stereotype.Service;

/**
 * Created by djabry on 05/01/15.
 */
@Dev
@Service
public class DevApplicationAccount extends BaseApplicationAccount {


    //@PostConstruct
    public void init() {
        DefaultSignUpRequest request = new DefaultSignUpRequest();
        request.setEmail(securityConfig.getApplicationEmail());
        request.setPassword(securityConfig.getApplicationPassword());
        request.setUsername(securityConfig.getApplicationName());
        this.authenticationService.signUp(request);

    }


}
