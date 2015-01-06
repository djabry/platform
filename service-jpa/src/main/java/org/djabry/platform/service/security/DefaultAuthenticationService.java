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

package org.djabry.platform.service.security;

import com.mysema.query.types.expr.BooleanExpression;
import lombok.extern.java.Log;
import org.djabry.platform.domain.api.SecurityToken;
import org.djabry.platform.domain.api.SignUpRequest;
import org.djabry.platform.persistence.jpa.entity.*;
import org.djabry.platform.service.api.AuthenticationService;
import org.djabry.platform.service.api.Hasher;
import org.djabry.platform.service.repository.AccountRepository;
import org.djabry.platform.service.repository.SecurityTokenRepository;
import org.djabry.platform.service.repository.UserRepository;
import org.djabry.platform.service.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by djabry on 04/01/15.
 */

@Service
@Log
@Transactional
public class DefaultAuthenticationService implements AuthenticationService<DBUser> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SecurityTokenRepository securityTokenRepository;

    @Autowired
    private Hasher hasher;

    @Autowired
    private SecurityConfig securityConfig;


    private DBUser currentUser;
    @Autowired
    private AuthenticationProvider authProvider;
    //@Autowired
    //private ApplicationAccount account;

    /**
     * @param username The username of the account
     * @param password The unencrypted password to log in with
     * @return The authentication token associated with the session
     */
    @Override
    public SecurityToken<DBUser> login(String username, String password) {

        BooleanExpression expression = QDBUserAccount.dBUserAccount.user.username.eq(username);
        DBUserAccount userSecurity = accountRepository.findOne(expression);
        if(userSecurity!=null){
            try {
                String hashedPassword = hasher.hashPassword(userSecurity.getId(),password);

                if(hashedPassword.equals(userSecurity.getEncryptedPassword())){
                    this.currentUser = userSecurity.getUser();
                    return this.createAndSave(userSecurity.getUser());
                }

            } catch (Exception e) {
                log.severe(e.getMessage());
            }

        }
        return null;

    }

    @Override
    public SecurityToken<DBUser> signUp(SignUpRequest request) {
        String username = request.getUsername();
        BooleanExpression exp = QDBUser.dBUser.username.eq(username);
        if (this.userRepository.findOne(exp) == null) {
             DBUser user = new DBUser();
             user.setEmail(request.getEmail());
             user.setUsername(username);

            DBUserAccount account = new DBUserAccount();

            account.setUser(user);
            try {
                String hashedPassword = hasher.hashPassword(account.getId(), request.getPassword());
                account.setEncryptedPassword(hashedPassword);
                account = this.accountRepository.save(account);


                return this.createAndSave(account.getUser());
            } catch (Exception e) {
                log.severe(e.getMessage());
            }



        }

        return null;
    }

    private DBSecurityToken createAndSave(DBUser user) {

        DBSecurityToken token = new DBSecurityToken();
        token.setUser(user);
        return this.securityTokenRepository.save(token);

    }

    /**
     * @param resetPasswordToken The token for resetting the password
     * @param newPassword        The new unencrypted password
     */
    @Override
    public boolean resetPassword(SecurityToken resetPasswordToken, String newPassword) {

        if (resetPasswordToken.getUser().equals(this.getCurrentUser())) {
            DBSecurityToken token = this.securityTokenRepository.findOne(resetPasswordToken.getId());
            if (token != null) {

                DBUserAccount account = this.accountRepository.findOne(QDBUserAccount.dBUserAccount.user.eq(token.getUser()));
                try {
                    String encryptedPassword = hasher.hashPassword(account.getId(), newPassword);
                    account.setEncryptedPassword(encryptedPassword);
                    this.accountRepository.save(account);
                } catch (Exception e) {
                    log.severe(e.getMessage());
                }

            }
        }

        return false;


    }

    /**
     * Change the current account password
     *
     * @param oldPassword The old unencrypted password
     */
    @Override
    public SecurityToken<DBUser> requestPasswordResetToken(String oldPassword) {

        DBUser currentUser = getCurrentUser();
        if(currentUser!=null ){

            BooleanExpression exp = QDBUserAccount.dBUserAccount.user.eq(currentUser);
            DBUserAccount userSecurity = accountRepository.findOne(exp);

            try {
                String hashedPassword = hasher.hashPassword(userSecurity.getId(),oldPassword);
                if(hashedPassword.equals(userSecurity.getEncryptedPassword())){

                    return this.createAndSave(userSecurity.getUser());

                }

            } catch (Exception e) {
                log.severe(e.getMessage());
            }

        }

        return null;

    }

    /**
     * @return The current logged in user
     */


    @Override
    public DBUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && currentUser != null) {
            String username = auth.getName();
            if (username.equals(currentUser.getUsername())) {
                return currentUser;
            }
        }

        currentUser = null;

        return null;
    }

    /**
     * Log out of the current account by signing into the application account
     */
    @Override
    public boolean logout() {

        SecurityContextHolder.getContext().setAuthentication(null);
        //account.signIn();
        return true;
    }


}
