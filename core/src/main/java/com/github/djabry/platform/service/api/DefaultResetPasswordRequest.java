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
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResetPasswordRequest implements ResetPasswordRequest {

    private String username;
    private String oldPassword;
}
