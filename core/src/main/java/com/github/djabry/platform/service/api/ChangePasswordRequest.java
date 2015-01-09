package com.github.djabry.platform.service.api;

import com.github.djabry.platform.domain.api.annotations.Password;
import com.github.djabry.platform.domain.api.annotations.UUID;

/**
 * Created by djabry on 09/01/15.
 */

public interface ChangePasswordRequest {

    /**
     * @return The id of the security token associated with the request to change password
     */
    @UUID
    String getSecurityTokenId();

    /**
     * @return The new unencrypted password
     */
    @Password
    String getNewPassword();
}
