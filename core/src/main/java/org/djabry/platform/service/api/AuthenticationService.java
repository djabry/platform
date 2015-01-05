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

import javax.validation.constraints.NotNull;

/**
 * Created by djabry on 03/01/15.
 *
 * The interface to mark authentication services
 *
 */
public interface AuthenticationService<U extends User> {

    /**
     * @param username The username of the account
     * @param password The unencrypted password to log in with
     * @return The authentication token associated with the session
     */
    SecurityToken<U> login(@NotNull String username,@NotNull String password);


    SecurityToken<U> signUp(SignUpRequest request);
    

    /**
     * @param resetPasswordToken The token for resetting the password
     * @param newPassword The new unencrypted password
     */
    boolean changePassword(@NotNull SecurityToken<U> resetPasswordToken,@NotNull String newPassword);


    /**
     *
     * Change the current account password
     *
     * @param oldPassword The old unencrypted password
     * @param newPassword The unencrypted password to change to
     *
     */
    boolean changePassword(@NotNull String oldPassword,@NotNull String newPassword);


    /**
     * @return The current logged in user
     */
    U getCurrentUser();


    /**
     * Log out of the current account
     */
    boolean logout();

}
