package com.github.djabry.platform.rest;

import com.github.djabry.platform.domain.api.SecurityToken;
import com.github.djabry.platform.domain.api.User;
import com.github.djabry.platform.service.api.*;
import com.github.djabry.platform.service.security.annotations.Authenticate;
import com.github.djabry.platform.service.security.annotations.ReadOwn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Created by djabry on 09/01/15.
 */
@RestController
public class AuthenticationController implements AuthenticationService {

    @Autowired
    private SpringAuthenticationService authenticationService;


    /**
     * @param request The credentials of the user attempting to log in
     * @return A security token on success, null otherwise
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(name = "/login", method = RequestMethod.POST)
    public SecurityToken login(@NotNull LoginRequest request) {
        return this.authenticationService.login(request);
    }

    /**
     * @param request The request containing the user credentials and details
     * @return A security token on success, null otherwise
     */
    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @RequestMapping(name = "/signup", method = RequestMethod.POST)
    public SecurityToken signUp(@NotNull SignUpRequest request) {
        return this.authenticationService.signUp(request);
    }

    /**
     * @param request The request to change the password
     * @return True if the password was changed successfully
     */
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(name = "/changepassword", method = RequestMethod.POST)
    public boolean changePassword(@NotNull ChangePasswordRequest request) {
        return this.authenticationService.changePassword(request);
    }

    /**
     * Request a token to reset the password of the current user
     *
     * @param request The request to reset the password
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(name = "/resetpassword", method = RequestMethod.POST)
    public SecurityToken requestPasswordResetToken(@NotNull ResetPasswordRequest request) {

        return this.authenticationService.requestPasswordResetToken(request);
    }

    /**
     * @return The current logged in user
     */
    @Override
    @ResponseBody
    @RequestMapping(name = "/user", method = RequestMethod.GET)
    @ReadOwn
    public User getCurrentUser() {
        return this.authenticationService.getCurrentUser();
    }

    /**
     * Log out of the current account
     */
    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(name = "/logout", method = RequestMethod.GET)
    @Authenticate
    public boolean logout() {

        return this.authenticationService.logout();
    }
}
