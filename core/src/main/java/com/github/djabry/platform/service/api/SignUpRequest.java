package com.github.djabry.platform.service.api;

import org.hibernate.validator.constraints.Email;

/**
 * Created by djabry on 05/01/15.
 */
public interface SignUpRequest extends LoginRequest {


    /**
     * @return The requested email
     */
    @Email
    public String getEmail();
}
