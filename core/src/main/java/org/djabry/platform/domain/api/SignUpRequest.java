package org.djabry.platform.domain.api;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created by djabry on 05/01/15.
 */
public interface SignUpRequest {
    
    @NotNull
    public String getUsername();
    @NotNull
    public String getPassword();
    @Email
    public String getEmail();
}
