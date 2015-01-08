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

package com.github.djabry.platform.service;

import com.github.djabry.platform.domain.api.Permission;
import com.github.djabry.platform.domain.api.SecurityToken;
import com.github.djabry.platform.persistence.jpa.entity.DBUserAccount;
import com.github.djabry.platform.persistence.jpa.entity.QDBUserAccount;
import com.github.djabry.platform.service.api.Hasher;
import com.github.djabry.platform.service.api.PermissionMapper;
import com.github.djabry.platform.service.api.SpringAuthenticationService;
import com.github.djabry.platform.service.config.TestConfig;
import com.github.djabry.platform.service.config.TestInitializer;
import com.github.djabry.platform.service.repository.AccountRepository;
import com.github.djabry.platform.service.repository.SecurityTokenRepository;
import com.github.djabry.platform.service.repository.UserRepository;
import com.github.djabry.platform.service.security.DefaultSignUpRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.*;


/**
 *
 * @author djabry
 */
@ContextConfiguration(classes = {TestInitializer.class, TestConfig.class})
@ActiveProfiles(profiles = "dev")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringAuthenticationServiceTest {
    
    @Autowired
    private SpringAuthenticationService springAuthenticationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SecurityTokenRepository securityTokenRepository;

    @Autowired
    private Hasher hasher;
    @Autowired
    private PermissionMapper mapper;

    @Before
    @Transactional
    public void clean() {

        securityTokenRepository.deleteAllInBatch();
        accountRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    public void testSignUp() {

        DefaultSignUpRequest request = new DefaultSignUpRequest();
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("test1");

        SecurityToken securityToken = springAuthenticationService.signUp(request);
        assertNotNull("Failed to create a new account", securityToken);
    }

    @Test
    public void testLogin(){

        testSignUp();
        SecurityToken login = springAuthenticationService.login("john", "test1");
        assertNotNull("Failed to log in", login);

    }

    @Test
    public void testHashPassword() {
        String password = UUID.randomUUID().toString();
        String salt = UUID.randomUUID().toString();
        String hashedPassword = null;
        String hashedPassword2 = null;
        try {
            hashedPassword = hasher.hashPassword(password, salt);
            hashedPassword2 = hasher.hashPassword(password, salt);


        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(hashedPassword);
        assertEquals("Hashed passwords don't match", hashedPassword, hashedPassword2);

    }

    @Test
    public void testGetPermissions() {
        System.out.println("Finding John's permissions");
        this.testSignUp();
        DBUserAccount john = this.accountRepository.findOne(QDBUserAccount.dBUserAccount.user.username.eq("john"));
        assertNotNull("User account not found", john);
        Set<Permission> permissions = mapper.mapPermissions(john.getRole());
        assertNotNull("Permissions are empty", permissions);

        assertTrue("Wrong number of permissions", permissions.size() > 3);

        Iterator<Permission> iterator = permissions.iterator();
        while (iterator.hasNext()) {
            Permission next = iterator.next();
            System.out.println(next);
        }

    }


}
