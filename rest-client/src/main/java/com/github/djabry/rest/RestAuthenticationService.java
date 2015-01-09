package com.github.djabry.rest;

import com.github.djabry.platform.domain.api.SecurityToken;
import com.github.djabry.platform.domain.api.User;
import com.github.djabry.platform.service.api.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

/**
 * Created by djabry on 09/01/15.
 */


public class RestAuthenticationService implements AuthenticationService {

    private final String restURL;

    private final RestTemplate restTemplate;


    public RestAuthenticationService(String url) {
        this.restURL = url;
        this.restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        DomainServices.getInstance().registerDomainService(this);
    }

    /**
     * @param loginRequest The request containing the user credentials
     * @return The security token associated with a successful request, null otherwise
     */
    @Override
    public SecurityToken login(@NotNull LoginRequest loginRequest) {
        return restTemplate.postForObject(this.restURL + "/login", loginRequest, SecurityToken.class);

    }

    /**
     * @param request The request containing the user credentials and details
     * @return The security token associated with a successful request, null otherwise
     */
    @Override
    public SecurityToken signUp(@NotNull SignUpRequest request) {
        return restTemplate.postForObject(this.restURL + "/signup", request, SecurityToken.class);
    }

    /**
     * @param request The request to change the password
     * @return True if the password was changed successfully, false otherwise
     */
    @Override
    public boolean changePassword(@NotNull ChangePasswordRequest request) {

        return restTemplate.postForObject(this.restURL + "/changepassword", request, Boolean.class);

    }

    /**
     * Request a token to reset the password of the current user
     *
     * @param request The request to reset the user password
     * @return The security token allowing the user to change password, null if unsuccessful
     */
    @Override
    public SecurityToken requestPasswordResetToken(@NotNull ResetPasswordRequest request) {
        return restTemplate.postForObject(this.restURL + "/resetpassword", request, SecurityToken.class);
    }

    /**
     * @return The current logged in user, null if none logged in
     */
    @Override
    public User getCurrentUser() {
        return restTemplate.getForObject(this.restURL + "/user", User.class);
    }

    /**
     * Log out of the current account
     */
    @Override
    public boolean logout() {
        return false;
    }
}
