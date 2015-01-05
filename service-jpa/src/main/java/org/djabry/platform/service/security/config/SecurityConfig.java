package org.djabry.platform.service.security.config;

import org.djabry.platform.domain.api.annotations.Password;
import org.djabry.platform.domain.api.annotations.UUID;
import org.djabry.platform.domain.api.annotations.Username;
import org.hibernate.validator.constraints.Email;

/**
 * Created by djabry on 05/01/15.
 */
public interface SecurityConfig {
    @UUID
    String getSalt();

    int getHashIterations();

    @Username
    String getApplicationName();

    @Password
    String getApplicationPassword();

    @Email
    String getApplicationEmail();


}
