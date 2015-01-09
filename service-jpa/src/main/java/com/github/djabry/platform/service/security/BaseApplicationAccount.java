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

package com.github.djabry.platform.service.security;

import com.github.djabry.platform.domain.api.SecurityToken;
import com.github.djabry.platform.service.api.SpringAuthenticationService;
import com.github.djabry.platform.service.security.config.SecurityConfig;
import lombok.extern.java.Log;
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
