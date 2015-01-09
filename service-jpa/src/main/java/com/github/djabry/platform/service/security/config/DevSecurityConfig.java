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

package com.github.djabry.platform.service.security.config;

import com.github.djabry.platform.service.profile.Dev;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Created by djabry on 05/01/15.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Configuration
@Dev
@Log
public class DevSecurityConfig extends BaseSecurityConfig {

    public static final String TEST_APPLICATION_NAME = UUID.randomUUID().toString();
    public static final String TEST_APPLICATION_PASSWORD = UUID.randomUUID().toString();
    public static final String TEST_APPLICATION_EMAIL = "test@test.com";

    /**
     * Since the dev profile uses an in-memory database, simply return a random string as the salt
     */
    private final String salt = UUID.randomUUID().toString();

    private final String applicationName = TEST_APPLICATION_NAME;

    private final String applicationPassword = TEST_APPLICATION_PASSWORD;

    private final String applicationEmail = TEST_APPLICATION_EMAIL;


}
