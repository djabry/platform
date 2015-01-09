package com.github.djabry.platform.service.api;

import com.github.djabry.platform.domain.api.annotations.Password;
import com.github.djabry.platform.domain.api.annotations.Username;

/**
 * Created by djabry on 09/01/15.
 */
public interface LoginRequest {
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
}
