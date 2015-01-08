package com.github.djabry.platform.domain.api;

import com.github.djabry.platform.domain.api.annotations.Password;
import com.github.djabry.platform.domain.api.annotations.Username;
import org.hibernate.validator.constraints.Email;

/**
 * Created by djabry on 05/01/15.
 */
public interface SignUpRequest {

    /**
     * @return The requested username
     */
    @Username
    public String getUsername();

    /**
     * @return The requested password
     */
    @Password
    public String getPassword();

    /**
     * @return The requested email
     */
    @Email
    public String getEmail();
}
