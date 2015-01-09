/*
 * Copyright (c) 2015 Daniel Jabry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.djabry.platform.test;

import com.github.djabry.platform.domain.api.SecurityToken;
import com.github.djabry.platform.service.api.*;
import junit.framework.TestCase;
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
        SecurityToken loginToken = authenticationService.login(this.generateLoginRequest(username, password));
        assertNotNull("Sign up securityToken not returned", loginToken);
        authenticationService.logout();

    }

    public void testChangePassword(String username, String oldPassword, String password) {
        AuthenticationService authenticationService = this.getAuthService();
        SecurityToken login = authenticationService.login(this.generateLoginRequest(username, password));
        DefaultResetPasswordRequest resetPasswordRequest = new DefaultResetPasswordRequest(login.getUser().getUsername(), oldPassword);
        SecurityToken securityToken = authenticationService.requestPasswordResetToken(resetPasswordRequest);
        assertNotNull("Failed to request change password token", securityToken);
        DefaultChangePasswordRequest changePasswordRequest = new DefaultChangePasswordRequest(securityToken.getId(), password);
        boolean passwordChanged = authenticationService.changePassword(changePasswordRequest);
        assertTrue("Failed to change password", passwordChanged);
        authenticationService.logout();

    }

    public SignUpRequest generateSignupRequest(final String username, final String password) {
        return new DefaultSignUpRequest(username, password, username + ".smith@test.com");

    }

    public LoginRequest generateLoginRequest(final String username, final String password) {
        return new DefaultLoginRequest(username, password);
        
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
