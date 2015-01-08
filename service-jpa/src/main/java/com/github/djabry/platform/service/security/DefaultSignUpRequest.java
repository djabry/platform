package com.github.djabry.platform.service.security;

import com.github.djabry.platform.domain.api.SignUpRequest;
import com.github.djabry.platform.domain.api.annotations.Password;
import com.github.djabry.platform.domain.api.annotations.Username;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

/**
 * Created by djabry on 05/01/15.
 */

@Data
public class DefaultSignUpRequest implements SignUpRequest {

    /**
     * The requested username
     */
    @Username
    String username;

    /**
     * The requested password
     */
    @Password
    String password;
    /**
     * The requested email
     */
    @Email
    String email;
}
