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
import org.djabry.platform.persistence.jpa.repository.DBRepository;
import org.djabry.platform.service.api.AuthenticationService;
import org.djabry.platform.service.api.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DBRepository<DBUser> userDBRepository;

    @Autowired
    private DBRepository<DBUserAccount> userSecurityDBRepository;

    @Autowired
    private DBRepository<DBSecurityToken> securityTokenDBRepository;

    @Autowired
    private Hasher hasher;

    /**
     * @param username The username of the account
     * @param password The unencrypted password to log in with
     * @return The authentication token associated with the session
     */
    @Override
    public SecurityToken<DBUser> login(String username, String password) {

        BooleanExpression expression = QDBUserAccount.dBUserAccount.user.username.eq(username);
        DBUserAccount userSecurity = userSecurityDBRepository.findOne(expression);
        if(userSecurity!=null){
            try {
                String hashedPassword = hasher.hashPassword(userSecurity.getId(),password);

                if(hashedPassword.equals(userSecurity.getEncryptedPassword())){

                    DBSecurityToken token = new DBSecurityToken();
                    token.setUser(userSecurity.getUser());
                    token = securityTokenDBRepository.save(token);
                    return token;

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
        if(this.userDBRepository.findOne(exp)==null){
             DBUser user = new DBUser();
             user.setEmail(request.getEmail());
             user.setUsername(username);
             
             DBSecurityToken token = new DBSecurityToken();
             token.setUser(user);
             token = this.securityTokenDBRepository.save(token);
            return token;
        }
        
        return null;
    }



    /**
     * @param resetPasswordToken The token for resetting the password
     * @param newPassword        The new unencrypted password
     */
    @Override
    public boolean changePassword(SecurityToken resetPasswordToken, String newPassword) {
        return false;
    }

    /**
     * Change the current account password
     *
     * @param oldPassword The old unencrypted password
     * @param newPassword The unencrypted password to change to
     */
    @Override
    public boolean changePassword(String oldPassword, String newPassword) {

        if(oldPassword.equals(newPassword)){
            return false;
        }

        DBUser currentUser = getCurrentUser();
        if(currentUser!=null ){


            BooleanExpression exp = QDBUserAccount.dBUserAccount.user.eq(currentUser);
            DBUserAccount userSecurity = userSecurityDBRepository.findOne(exp);

            try {
                String hashedPassword = hasher.hashPassword(userSecurity.getId(),oldPassword);
                if(hashedPassword.equals(userSecurity.getEncryptedPassword())){

                    String newHashedPassword = hasher.hashPassword(userSecurity.getId(),newPassword);
                    userSecurity.setEncryptedPassword(newHashedPassword);
                    userSecurityDBRepository.save(userSecurity);
                    return true;

                }

            } catch (Exception e) {
                log.severe(e.getMessage());
            }





        }

        return false;

    }

    /**
     * @return The current logged in user
     */
    @Override
    public DBUser getCurrentUser() {
        return null;
    }

    /**
     * Log out of the current account
     */
    @Override
    public boolean logout() {
        return false;
    }



}
