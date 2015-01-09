package com.github.djabry.platform.service.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by djabry on 09/01/15.
 */

@Data
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class DefaultChangePasswordRequest implements ChangePasswordRequest {

    /**
     * The security token id associated with the request to change password
     */
    private String securityTokenId;


    /**
     * The new unencrypted password
     */
    private String newPassword;
}
