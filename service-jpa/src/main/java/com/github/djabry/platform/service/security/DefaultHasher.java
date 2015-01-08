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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.djabry.platform.service.security;


import com.github.djabry.platform.service.api.Hasher;
import com.github.djabry.platform.service.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author djabry
 */

@Service
public class DefaultHasher implements Hasher {
        /**
     * Add additional salt to password hashing
     */


        private ShaPasswordEncoder passwordEncoder;
    private SecurityConfig securityConfig;

    @Autowired
    public DefaultHasher(SecurityConfig config) {

        passwordEncoder = new ShaPasswordEncoder(256);
        passwordEncoder.setIterations(config.getHashIterations());
        this.securityConfig = config;
    }
    
     /**
     * Hash the password using salt values
     * See https://www.owasp.org/index.php/Hashing_Java
     *
     * @param passwordToHash
     * @return hashed password
     */
    @Override
    public String hashPassword(String uuid, String passwordToHash) throws Exception {
        return hashToken(passwordToHash, uuid + securityConfig.getSalt());
    }


    private String hashToken(String token, String salt) throws Exception {

        return passwordEncoder.encodePassword(token, salt);

    }


}
