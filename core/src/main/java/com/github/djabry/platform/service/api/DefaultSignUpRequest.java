package com.github.djabry.platform.service.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by djabry on 05/01/15.
 */

@Data
@NoArgsConstructor
@XmlRootElement
public class DefaultSignUpRequest extends DefaultLoginRequest implements SignUpRequest, Serializable {

    /**
     * The requested email
     */
    @Email
    String email;


    /**
     * @param username The requested username
     * @param password The requested password
     * @param email    The requested email
     */
    public DefaultSignUpRequest(String username, String password, String email) {
        super(username, password);
        this.email = email;
    }
}
