package org.djabry.platform.service.api;

import org.djabry.platform.domain.api.SecurityToken;
import org.djabry.platform.domain.api.SignUpRequest;
import org.djabry.platform.domain.api.User;
import org.djabry.platform.domain.api.annotations.Password;
import org.djabry.platform.domain.api.annotations.Username;

import javax.validation.constraints.NotNull;

/**
 * Created by djabry on 07/01/15.
 */
public interface AuthenticationService<U extends User> extends DomainService {

    /**
     * This method is to enable a normal user/administrator to log in
     *
     * @param username The username of the account
     * @param password The unencrypted password to log in with
     * @return The authentication token associated with the session
     */

    SecurityToken<U> login(@Username String username, @Password String password);


    //@Authenticate
    SecurityToken<U> signUp(@NotNull SignUpRequest request);


    /**
     * @param resetPasswordToken The token for resetting the password
     * @param newPassword        The new unencrypted password
     */

    boolean resetPassword(@NotNull SecurityToken<U> resetPasswordToken, @Password String newPassword);


    /**
     * Request a token to reset the password of the current user
     *
     * @param oldPassword The old unencrypted password
     */

    SecurityToken<U> requestPasswordResetToken(@Password String oldPassword);


    /**
     * @return The current logged in user
     */

    U getCurrentUser();


    /**
     * Log out of the current account
     */

    boolean logout();
}
