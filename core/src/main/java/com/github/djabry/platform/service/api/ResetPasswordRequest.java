package com.github.djabry.platform.service.api;

import com.github.djabry.platform.domain.api.annotations.Password;
import com.github.djabry.platform.domain.api.annotations.Username;

/**
 * Created by djabry on 09/01/15.
 */
public interface ResetPasswordRequest {

    @Username
    String getUsername();

    @Password
    String getOldPassword();

}
