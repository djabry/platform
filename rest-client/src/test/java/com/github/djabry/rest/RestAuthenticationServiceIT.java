package com.github.djabry.rest;

import com.github.djabry.platform.service.api.DomainServices;
import com.github.djabry.platform.test.AuthenticationServiceIT;
import org.junit.Test;

/**
 * Created by djabry on 09/01/15.
 */


public class RestAuthenticationServiceIT extends AuthenticationServiceIT {
    @Test
    public void testAll() {
        DomainServices.getInstance().registerDomainService(new RestAuthenticationService("http://localhost:8080"));
        super.testAll();

    }
}
