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

import com.github.djabry.platform.service.api.DefaultSignUpRequest;
import com.github.djabry.platform.service.profile.Dev;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by djabry on 05/01/15.
 */
@Dev
@Service
public class DevApplicationAccount extends BaseApplicationAccount {


    @PostConstruct
    public void init() {
        DefaultSignUpRequest request = new DefaultSignUpRequest();
        request.setEmail(securityConfig.getApplicationEmail());
        request.setPassword(securityConfig.getApplicationPassword());
        request.setUsername(securityConfig.getApplicationName());
        this.springAuthenticationService.signUp(request);
        this.signIn();

    }


}
