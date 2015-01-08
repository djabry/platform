package com.github.djabry.platform.service.security.config;

import com.github.djabry.platform.service.profile.Dev;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Created by djabry on 05/01/15.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Configuration
@Dev
@Log
public class DevSecurityConfig extends BaseSecurityConfig {

    public static final String TEST_APPLICATION_NAME = UUID.randomUUID().toString();
    public static final String TEST_APPLICATION_PASSWORD = UUID.randomUUID().toString();
    public static final String TEST_APPLICATION_EMAIL = "test@test.com";

    /**
     * Since the dev profile uses an in-memory database, simply return a random string as the salt
     */
    private final String salt = UUID.randomUUID().toString();

    private final String applicationName = TEST_APPLICATION_NAME;

    private final String applicationPassword = TEST_APPLICATION_PASSWORD;

    private final String applicationEmail = TEST_APPLICATION_EMAIL;


}
