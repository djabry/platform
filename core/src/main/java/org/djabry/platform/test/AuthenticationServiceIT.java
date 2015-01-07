package org.djabry.platform.test;

import junit.framework.TestCase;
import org.djabry.platform.domain.api.SecurityToken;
import org.djabry.platform.domain.api.SignUpRequest;
import org.djabry.platform.service.api.AuthenticationService;
import org.djabry.platform.service.api.DomainServices;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by djabry on 07/01/15.
 */

@Category(IntegrationTest.class)
public class AuthenticationServiceIT extends TestCase {


    public void testSignup(String username, String password) {

        AuthenticationService authenticationService = this.getAuthService();

        SignUpRequest signUpRequest = this.generateSignupRequest(username, password);
        SecurityToken securityToken = authenticationService.signUp(signUpRequest);
        assertNotNull("Sign up securityToken not returned", securityToken);

    }

    public void testLogin(String username, String password) {

        AuthenticationService authenticationService = this.getAuthService();
        SecurityToken loginToken = authenticationService.login(username, password);
        assertNotNull("Sign up securityToken not returned", loginToken);
        authenticationService.logout();

    }

    public void testChangePassword(String username, String oldPassword, String password) {
        AuthenticationService authenticationService = this.getAuthService();
        authenticationService.login(username, oldPassword);
        SecurityToken securityToken = authenticationService.requestPasswordResetToken(oldPassword);
        assertNotNull("Failed to request change password token", securityToken);
        boolean passwordChanged = authenticationService.resetPassword(securityToken, password);
        assertTrue("Failed to change password", passwordChanged);
        authenticationService.logout();

    }

    public SignUpRequest generateSignupRequest(final String username, final String password) {

        return new SignUpRequest() {
            /**
             * @return The requested username
             */
            @Override
            public String getUsername() {
                return username;
            }

            /**
             * @return The requested password
             */
            @Override
            public String getPassword() {
                return password;
            }

            /**
             * @return The requested email
             */
            @Override
            public String getEmail() {
                return username + "." + password + "@example.com";
            }
        };
    }

    @Test
    public void testAll() {

        final String username = "John";
        final String password = "password";
        this.testSignup(username, password);
        this.testLogin(username, password);
        String newPassword = "newPassword";
        this.testChangePassword(username, password, newPassword);
    }

    public AuthenticationService getAuthService() {
        AuthenticationService service = DomainServices.getInstance().findService(AuthenticationService.class);
        assertNotNull(service);
        return service;

    }


}
