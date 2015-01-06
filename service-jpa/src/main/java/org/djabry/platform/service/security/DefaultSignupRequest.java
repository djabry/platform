package org.djabry.platform.service.security;

import lombok.Data;
import org.djabry.platform.domain.api.SignUpRequest;
import org.djabry.platform.domain.api.annotations.Password;
import org.djabry.platform.domain.api.annotations.Username;
import org.hibernate.validator.constraints.Email;

/**
 * Created by djabry on 05/01/15.
 */

@Data
public class DefaultSignupRequest implements SignUpRequest {

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
