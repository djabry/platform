package com.github.djabry.platform.service.api;

import com.github.djabry.platform.domain.api.SecurityToken;
import com.github.djabry.platform.domain.api.User;

import javax.validation.constraints.NotNull;

/**
 * Created by djabry on 07/01/15.
 */
public interface AuthenticationService<U extends User> extends DomainService {


    /**
     * @param loginRequest The request containing the user credentials
     * @return The security token associated with a successful request, null otherwise
     */
    SecurityToken<U> login(@NotNull LoginRequest loginRequest);


    /**
     * @param request The request containing the user credentials and details
     * @return The security token associated with a successful request, null otherwise
     */
    SecurityToken<U> signUp(@NotNull SignUpRequest request);


    /**
     * @param request The request to change the password
     * @return True if the password was changed successfully, false otherwise
     */

    boolean changePassword(@NotNull ChangePasswordRequest request);


    /**
     * Request a token to reset the password of the current user
     *
     * @param request The request to reset the user password
     * @return The security token allowing the user to change password, null if unsuccessful
     */

    SecurityToken<U> requestPasswordResetToken(@NotNull ResetPasswordRequest request);


    /**
     * @return The current logged in user, null if none logged in
     */

    U getCurrentUser();


    /**
     * Log out of the current account
     */

    boolean logout();
}
