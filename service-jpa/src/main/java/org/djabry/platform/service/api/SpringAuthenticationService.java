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

package org.djabry.platform.service.api;

import org.djabry.platform.domain.api.SecurityToken;
import org.djabry.platform.domain.api.SignUpRequest;
import org.djabry.platform.domain.api.User;
import org.djabry.platform.domain.api.annotations.Password;
import org.djabry.platform.domain.api.annotations.Username;
import org.djabry.platform.service.security.annotations.Authenticate;
import org.djabry.platform.service.security.annotations.ReadOwn;

import javax.validation.constraints.NotNull;

/**
 * Created by djabry on 03/01/15.
 *
 * The interface to mark spring authentication
 *
 */
public interface SpringAuthenticationService<U extends User> extends AuthenticationService<U> {

    /**
     * This method is to enable a normal user/administrator to log in
     * @param username The username of the account
     * @param password The unencrypted password to log in with
     * @return The authentication token associated with the session
     */

    SecurityToken<U> login(@Username String username, @Password String password);


    //@Authenticate
    SecurityToken<U> signUp(@NotNull SignUpRequest request);
    

    /**
     * @param resetPasswordToken The token for resetting the password
     * @param newPassword The new unencrypted password
     */
    @Authenticate
    boolean resetPassword(@NotNull SecurityToken<U> resetPasswordToken, @Password String newPassword);
    

    /**
     *
     * Request a token to reset the password of the current user
     * @param oldPassword The old unencrypted password
     *
     */
    @ReadOwn
    SecurityToken<U> requestPasswordResetToken(@Password String oldPassword);


    /**
     * @return The current logged in user
     */
    @ReadOwn
    U getCurrentUser();


    /**
     * Log out of the current account
     */
    @Authenticate
    boolean logout();

}
