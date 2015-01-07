package org.djabry.platform.service.security.config;

import lombok.Data;
import org.djabry.platform.service.api.SpringAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by djabry on 05/01/15.
 */
@Data
public abstract class BaseSecurityConfig implements SecurityConfig {
    private final int hashIterations = 1000;

    @Autowired
    private SpringAuthenticationService springAuthenticationService;


}
